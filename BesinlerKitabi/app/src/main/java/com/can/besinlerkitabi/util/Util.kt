package com.can.besinlerkitabi.util

import android.content.Context
import android.widget.ImageView
import androidx.constraintlayout.widget.Placeholder
import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.can.besinlerkitabi.R

/*
fun String.benimEklentim( parametre : String){

    println(parametre)
}

 */

fun ImageView.gorselIndir(url : String?,placeholder: CircularProgressDrawable){

    val options = RequestOptions().placeholder(placeholder).error(R.mipmap.ic_launcher_round)

    Glide.with(context).setDefaultRequestOptions(options).load(url).into(this)

}


fun placeholderYap(context: Context) : CircularProgressDrawable {

    return CircularProgressDrawable(context).apply {

        strokeWidth = 8f
        centerRadius = 40f
        start()

    }
}

//DATA BINDING
@BindingAdapter("android:dowloadImage")
fun dowloadImage(view : ImageView,url: String?){

    view.gorselIndir(url, placeholderYap(view.context))

}