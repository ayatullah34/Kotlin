package com.can.superkahramankitabi

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import kotlin.collections.ArrayList as ArrayList1

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Veri Hazırlığı

        var superKahramanIsimleri = ArrayList<String> ()
        superKahramanIsimleri.add("Batman")
        superKahramanIsimleri.add("Superman")
        superKahramanIsimleri.add("Ironman")
        superKahramanIsimleri.add("Spiderman")
        superKahramanIsimleri.add("Aquaman")

        //Verimsiz Tanımlamalar

        /*
        val batmanBitmap = BitmapFactory.decodeResource(applicationContext.resources,R.drawable.batman)
        val supermanBitmap = BitmapFactory.decodeResource(applicationContext.resources,R.drawable.superman)
        val ironmanBitmap = BitmapFactory.decodeResource(applicationContext.resources,R.drawable.ironman)
        val spidermanBitmap = BitmapFactory.decodeResource(applicationContext.resources,R.drawable.spiderman)
        val aquamanBitmap = BitmapFactory.decodeResource(applicationContext.resources,R.drawable.aquaman)


        var superKahramanGorselleri = ArrayList<Bitmap>()
        superKahramanGorselleri.add(batmanBitmap)
        superKahramanGorselleri.add(supermanBitmap)
        superKahramanGorselleri.add(ironmanBitmap)
        superKahramanGorselleri.add(spidermanBitmap)
        superKahramanGorselleri.add(aquamanBitmap)

         */

        //-----------VERİMLİ TANIMLAMALAR--------------

        val batmanDrawableId = R.drawable.batman
        val supermanDrawableId = R.drawable.superman
        val ironmanDrawableId = R.drawable.ironman
        val spidermanDrawableId = R.drawable.spiderman
        val aquamanDrawableId = R.drawable.aquaman

        var superKahramanDrawableListesi = ArrayList<Int>()
        superKahramanDrawableListesi.add(batmanDrawableId)
        superKahramanDrawableListesi.add(supermanDrawableId)
        superKahramanDrawableListesi.add(ironmanDrawableId)
        superKahramanDrawableListesi.add(spidermanDrawableId)
        superKahramanDrawableListesi.add(aquamanDrawableId)


        //------------ADAPTER-------------

        val layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager

        /*
        val adapter =RecyclerAdapter(superKahramanIsimleri,superKahramanGorselleri)

         */

        val adapter =RecyclerAdapter(superKahramanIsimleri,superKahramanDrawableListesi)
        recyclerView.adapter = adapter


    }

}