package com.cupcake.ui

import android.view.View
import android.widget.EditText
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.cupcake.viewmodels.base.BaseAdapter


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

@BindingAdapter(value = ["app:items"])
fun <T> setRecyclerItems(view: RecyclerView, items: List<T>?) {
    items?.let {
        (view.adapter as BaseAdapter<T>).setData(items)
    } ?: (view.adapter as BaseAdapter<T>).setData(emptyList())
}

@BindingAdapter(value = ["app:showWhenLoading"])
fun <T> showWhenLoading(view: View, state: Boolean?) {
    state?.let {
        view.visibility = if (state) View.VISIBLE else View.INVISIBLE
    }
}


