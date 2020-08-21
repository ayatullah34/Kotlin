package com.can.oop

//open inherit olmasına imkan sağlıyor
 open class Sanatci(isim : String, yas : Int, meslek : String) {

    //ENCAPSULATION
     var isim : String? =isim
         private set   //isime ulaşılıp değiştirmeyi engeller
         get

     var yas : Int? = yas
         private set
         get

    private var meslek : String? = meslek

}