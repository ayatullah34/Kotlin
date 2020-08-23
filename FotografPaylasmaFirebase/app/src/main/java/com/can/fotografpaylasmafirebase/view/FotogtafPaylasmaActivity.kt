package com.can.fotografpaylasmafirebase.view

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.can.fotografpaylasmafirebase.R
import com.google.firebase.Timestamp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import kotlinx.android.synthetic.main.activity_fotogtaf_paylasma.*
import java.util.*

class FotogtafPaylasmaActivity : AppCompatActivity() {

    var secilenGorsel : Uri? = null
    var secilenBitmap : Bitmap? = null

    //-------------------GÖRSEL KAYDETME---------------------------------
    private lateinit var storage : FirebaseStorage
    private lateinit var auth : FirebaseAuth
    private lateinit var database: FirebaseFirestore

        override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fotogtaf_paylasma)

            storage = FirebaseStorage.getInstance()
            auth = FirebaseAuth.getInstance()
            database = FirebaseFirestore.getInstance()


        }



    fun paylas(view: View){

        //depo işlemleri
        //UUID -> UNIVERSAL UNIQUE ID -- random id veriyo her yüklenecek resim için
        val uuid = UUID.randomUUID()
        val gorselIsmi = "${uuid}.jpg"

        //firebase deki storage kısmına gider
        val reference = storage.reference
        //firebase deki storage kısmındaki image klasörüne ulaştık
        val gorselRefence = reference.child("images").child(gorselIsmi)

        if(secilenGorsel != null){
            gorselRefence.putFile(secilenGorsel!!).addOnSuccessListener { taskSnapshot ->

                val yuklenenGorselReference = FirebaseStorage.getInstance().reference.child("images").child(gorselIsmi)
                yuklenenGorselReference.downloadUrl.addOnSuccessListener { uri ->
                    val dowloadUrl= uri.toString()
                    val guncelKullaniciEmaili = auth.currentUser!!.email.toString()
                    val kullaniciYorumu = yorumText.text.toString()
                    val tarih = Timestamp.now()

                    //VERİ TABANI İŞLEMLERİ
                    val postHashMap = hashMapOf< String,Any >()
                    postHashMap.put("gorselurl",dowloadUrl)
                    postHashMap.put("kullaniciemail",guncelKullaniciEmaili)
                    postHashMap.put("kullaniciyorum",kullaniciYorumu)
                    postHashMap.put("tarih",tarih)


                    database.collection("Post").add(postHashMap).addOnCompleteListener { task ->
                        if(task.isSuccessful){
                            finish()
                        }

                    }.addOnFailureListener{ exception ->
                        Toast.makeText(applicationContext,exception.localizedMessage,Toast.LENGTH_LONG).show()
                    }

                }.addOnFailureListener { exception ->
                    Toast.makeText(applicationContext, exception.localizedMessage, Toast.LENGTH_LONG).show()
                }

            }
        }


    }

    //-------------------GÖRSEL SEÇME---------------------------------
    fun gorselSec(view: View){

        if(ContextCompat.checkSelfPermission(this,Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
            //izin verilmemişse

            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),1)

        }
        else{
        //izin alınmışsa

            val galeriIntent = Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            startActivityForResult(galeriIntent,2)

        }

    }

//-------------------GALERİ KONTROLLERİ---------------------------------
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {

        if(requestCode == 1){

            if(grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                //izin verilmiş
                val galeriIntent = Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                startActivityForResult(galeriIntent,2)

            }

        }

        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        if(requestCode == 2 && resultCode == Activity.RESULT_OK && data != null){

            secilenGorsel = data.data

            if(secilenGorsel != null){

                if(Build.VERSION.SDK_INT >= 28){

                    val source = ImageDecoder.createSource(this.contentResolver,secilenGorsel!!)
                    secilenBitmap = ImageDecoder.decodeBitmap(source)
                    imageView.setImageBitmap(secilenBitmap)
                }
                else{

                    secilenBitmap = MediaStore.Images.Media.getBitmap(this.contentResolver,secilenGorsel)
                    imageView.setImageBitmap(secilenBitmap)
                }

            }

        }

        super.onActivityResult(requestCode, resultCode, data)
    }



}