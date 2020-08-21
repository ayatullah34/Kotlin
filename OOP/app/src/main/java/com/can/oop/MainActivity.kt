package com.can.oop

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        println("----------CLASSES-----------")
        val kullanici = Kullanici("ali",70)
        val digerKullanici = Kullanici ("veli",44)


        println("----------ENCAPSULATION----------")
        val serdar = Sanatci("serdar",32,"doktor")
        println(serdar.isim)


        println("----------INHERITANCE----------")
        val mehmet = OzelSanatci("mehmet",22,"şarkıcı")
        mehmet.sarkiSoyle()


        println("----------POLYMORPHİSİM----------")

        //Statik Polymorphism
        val islem = Islemler()
        println(islem.carpma())
        println(islem.carpma(2,3))
        println(islem.carpma(2,3,4))


        //Dinamik Polymorphism
        val barley = Kopek()
        barley.sesCikar()

        val kedi = Hayvan()
        kedi.sesCikar()

        barley.kopekFonksiyonu()


        println("----------Abstraction & Interface----------")
        kullanici.insanFonksiyonu()

        var gitar = Gitar()
        gitar.marka = "Gitar Markası"
        gitar.elektro = true

        gitar.bilgi()
        println(gitar.oda)



        println("----------Lambda Expression----------")
        yazdigimiYazdir("test")

        val yazdigimiYazdirLambda = {verilenString : String -> println(verilenString)}
        yazdigimiYazdirLambda("test lambda")

        val carpmaIslemiLambda = { a: Int, b : Int -> a * b }
        println(carpmaIslemiLambda(2,3))

        val carpmaLambdaV2 : ( Int, Int ) -> Int = { a, b -> a *b }
        println(carpmaLambdaV2(3,4))
    }


    fun yazdigimiYazdir( string: String){
        println(string)
    }

}