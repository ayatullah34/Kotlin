package com.can.contextproject


import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //Toast, Alert

        //applicationContext -> app context
        //this, this@MainActivity -> aktivitenin contexti


        Toast.makeText(this,"Hoşgeldin!",Toast.LENGTH_SHORT).show()

    }


    fun mesajGoster(view: View){

        val uyariMesaji = AlertDialog.Builder(this@MainActivity)
        uyariMesaji.setTitle("Şifre Hatası")
        uyariMesaji.setMessage("Şifre yanlış,tekrar denemek ister misiniz!")
        uyariMesaji.setPositiveButton("Evet",DialogInterface.OnClickListener { dialogInterface, i ->

            Toast.makeText(this,"Baştan deneniyor",Toast.LENGTH_SHORT).show()
        })

        uyariMesaji.setNegativeButton("Hayır"){dialogInterface, i ->
            Toast.makeText(this,"reddedildi",Toast.LENGTH_SHORT).show()
        }

        uyariMesaji.show()

    }
}