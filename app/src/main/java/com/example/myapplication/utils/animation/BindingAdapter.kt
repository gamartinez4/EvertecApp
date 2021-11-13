package com.example.myapplication.utils.animation

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("android:loadImage")
fun imageURLLoad(imageView: ImageView, url:String?){
    url?.let{Glide.with(imageView).load(it).into(imageView)}
}