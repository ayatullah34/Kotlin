package com.can.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        val gelenVeri = intent
        val alinanVeri = intent.getStringExtra("yollananVeri")
        textView2.text = alinanVeri
    }


    fun Return(view : View){

        val intent = Intent(applicationContext,MainActivity::class.java)
        startActivity(intent)
        finish()

    }

}