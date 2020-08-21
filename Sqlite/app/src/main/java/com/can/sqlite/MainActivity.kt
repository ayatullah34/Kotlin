package com.can.sqlite

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import java.lang.Exception

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        try{

            //---------------VERİTABANI OLUŞTURMA-------------
            val veritabani = this.openOrCreateDatabase("Urunler", Context.MODE_PRIVATE,null)
            veritabani.execSQL("create table if not exists urunler(id integer primary key,isim varchar,fiyat int) ")


            //---------------VERİTABANINA EKLEME-------------
            //veritabani.execSQL("insert into urunler(isim,fiyat) values('ayakkabi',100)")
            //veritabani.execSQL("INSERT INTO urunler (isim, fiyat) VALUES ('Elbise',150)")
            //veritabani.execSQL("INSERT INTO urunler (isim, fiyat) VALUES ('Tshirt',50)")
            //veritabani.execSQL("INSERT INTO urunler (isim, fiyat) VALUES ('Atki',200)")
            //veritabani.execSQL("INSERT INTO urunler (isim, fiyat) VALUES ('Bere',10)")

            //---------------VERİTABANINDAN ELEMAN SİLME VE GÜNCELLEME-------------
            //veritabani.execSQL("DELETE FROM urunler WHERE id = 5")
            //veritabani.execSQL("UPDATE urunler SET fiyat = 250 WHERE isim ='Elbise'")
            //veritabani.execSQL("UPDATE urunler SET isim = 'Ayakkab' WHERE id = 1")



            //---------------VERİ SEÇME-------------
            val cursor = veritabani.rawQuery("select * from urunler",null)
            //val cursor = veritabani.rawQuery("SELECT * FROM urunler WHERE isim LIKE '%e'",null)
            //val cursor = veritabani.rawQuery("SELECT * FROM urunler WHERE id = 3",null)
            //val cursor = veritabani.rawQuery("SELECT * FROM urunler WHERE isim = 'Bere'",null)


            val idColumnIndex = cursor.getColumnIndex("id")
            val isimColumnIndex = cursor.getColumnIndex("isim")
            val fiyatColumnIndex = cursor.getColumnIndex("fiyat")

            while (cursor.moveToNext()){

                println("ID : ${cursor.getInt(idColumnIndex)}")
                println("Isim : ${cursor.getString(isimColumnIndex)}")
                println("Fiyat : ${cursor.getInt(fiyatColumnIndex)}")

            }

            cursor.close()


        }catch ( e : Exception){

            e.printStackTrace()
        }
    }

}