package com.can.superkahramanprojesi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }


    fun creating(view: View){

        val name = nameText.text.toString()
        val age=ageText.text.toString().toIntOrNull()
        val job=jobText.text.toString()

        if(age ==null){
            resultText.text="int değer giriniz"
        }
        else{

            val superHero = SuperHero(name,age,job)
            resultText.text = "Name: ${superHero.name} Age: ${superHero.age} Job: ${superHero.job}"
            //resultText.text = "Name: ${name} Age: ${age} Job: ${job}"
        }




    }


}