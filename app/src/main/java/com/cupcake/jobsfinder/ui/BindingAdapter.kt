package com.cupcake.jobsfinder.ui

import android.widget.EditText
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import coil.load
import com.cupcake.jobsfinder.R


@BindingAdapter(value = ["app:loadImage"])
fun loadImageCoil(image: ImageView, url: String?) {
    image.load(url)
}

@BindingAdapter("setIcons")
fun setIcons(editText: EditText, isValid: Boolean) {
    val text = editText.text?.toString()?.trim()
    if (text.isNullOrEmpty()) {
        editText.setCompoundDrawablesRelativeWithIntrinsicBounds(
            null, null, null, null
        )
    } else {
        val endIconDrawable = if (isValid && text.isNotEmpty()) {
            ContextCompat.getDrawable(editText.context, R.drawable.ic_vaild)
        } else {
            ContextCompat.getDrawable(editText.context, R.drawable.ic_invaild)
        }

        editText.setCompoundDrawablesRelativeWithIntrinsicBounds(
            null, null, endIconDrawable, null
        )
    }
}



