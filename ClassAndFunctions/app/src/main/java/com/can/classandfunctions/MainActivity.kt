package com.can.classandfunctions

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        var j = -10

        if (j == 0) {
            ilkFonksiyon()
        } else {
            ikinciFonksiyon()
        }

        cikarma(130,20)

        var sum = toplama(1900,5)
        println(sum)
        textView.text = "Sonuç: ${sum}"

        //----------BUTTON 2.YOL----------------
        button.setOnClickListener {
            val sumResult = toplama(10,5)
            textView.text = "Sonuç: ${sumResult}"
        }

        classExamples()

        nullGuvenligi()

    }


    fun ilkFonksiyon() {
        println("ilk fonksiyon çalıştırıldı")
    }

    fun ikinciFonksiyon() {
        println("ikinci fonksiyon çalıştırıldı")
    }


    //Girdi Almak
    fun cikarma(x : Int,y : Int){

        textView.text = "Sonuç: ${x-y}"
    }

    fun toplama(x : Int,y : Int) : Int{

        return x + y
    }

    /*
    fun degistir(view: View){
        val sumResult = toplama(10,5)
        textView.text = "Sonuç: ${sumResult}"
    }
    */


    //----------CLASSES----------------
    fun classExamples(){

        var superman = SuperKahraman("Superman",45,"Gazeteci")
        /*
        superman.isim="Superman"
        superman.yas=45
        superman.meslek="Gazeteci"
        */

        textView.text = "Yaş :${superman.yas}"
    }

    fun nullGuvenligi() {
        //Null, Nullability, Null Safety

        //Tanımlama, Definening
        var benimString: String?

        //Initialization
        benimString = "Atıl"

        var benimYasim: Int? = null
        println(benimYasim)

        //benimYasim = 2

        //1
        if (benimYasim != null) {
            println(benimYasim * 2)
        } else {
            println("null geldi")
        }

        //2
        // !! -> null olmayacak, ? -> null olabilir
        println(benimYasim?.minus(2))

        //3
        //elvis operatörü

        //benimYasim = 10
        val sonuc = benimYasim?.minus(2) ?: 10
        println(sonuc)

        //4
        //let
        //benimYasim = 5
        benimYasim?.let {
            println(it * 5)
        }
    }
    


    }