package com.cupcake.jobsfinder.ui

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import coil.load


@BindingAdapter(value = ["app:loadImage"])
fun loadImageCoil(image : ImageView , url : String?) {
    image.load(url)
}