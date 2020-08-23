package com.can.besinlerkitabi.servis

import com.can.besinlerkitabi.model.Besin
import io.reactivex.Single
import retrofit2.Call
import retrofit2.http.GET

//------------------------RETROFİT-----------------
interface BesinAPI {
    //GET,POST  internetten veri çekme, internete veri yollama

    //https://raw.githubusercontent.com/atilsamancioglu/BTK20-JSONVeriSeti/master/besinler.json

    //BASE_URL -> https://raw.githubusercontent.com/
    //eklenti kısmı -> atilsamancioglu/BTK20-JSONVeriSeti/master/besinler.json

    @GET("atilsamancioglu/BTK20-JSONVeriSeti/master/besinler.json")

    //RETROFİT KULLANIMI -->> fun getBesin() : Call<List<Besin>>

    //RX JAVA KULLANIMI
    fun getBesin() : Single<List<Besin>>



}