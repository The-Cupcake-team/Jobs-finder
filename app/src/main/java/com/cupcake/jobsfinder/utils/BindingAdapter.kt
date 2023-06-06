package com.cupcake.jobsfinder.utils

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.cupcake.jobsfinder.ui.base.BaseAdapter

@BindingAdapter(value = ["app:items"])
fun <T> setRecyclerItems(view: RecyclerView, items: List<T>?) {
    items?.let {
        (view.adapter as BaseAdapter<T>).setData(items)
    } ?: (view.adapter as BaseAdapter<T>).setData(emptyList())
}