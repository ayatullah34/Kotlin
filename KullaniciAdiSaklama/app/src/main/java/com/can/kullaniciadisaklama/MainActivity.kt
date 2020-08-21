package com.can.kullaniciadisaklama

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var sharedPreferences : SharedPreferences
    var alinanKullanici : String? =null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sharedPreferences = this.getSharedPreferences("com.can.kullaniciadisaklama",
            Context.MODE_PRIVATE)

        alinanKullanici = sharedPreferences.getString("kullanici","")

        if(alinanKullanici != ""){
            textView.text = "Kaydedilen Kullanıcı Adı : ${alinanKullanici}"
        }


    }


    fun kaydet(view: View){

        val kullaniciAdi = editText.text.toString()

        if(kullaniciAdi == ""){
            Toast.makeText(this,"Kullanıcı Adını Giriniz",Toast.LENGTH_SHORT).show()
        }
        else{
            sharedPreferences.edit().putString("kullanici",kullaniciAdi).apply()
            textView.text = "Kaydedilen Kullanıcı Adı : ${kullaniciAdi}"
        }

    }

    fun sil(view: View){

        alinanKullanici = sharedPreferences.getString("kullanici","")

        if(alinanKullanici != null){

            textView.text = "Kaydedilen Kullanıcı Adı : "
            sharedPreferences.edit().remove("kullanici").apply()
        }
    }
}