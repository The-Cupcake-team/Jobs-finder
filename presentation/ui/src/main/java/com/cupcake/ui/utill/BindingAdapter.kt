package com.cupcake.ui.utill


import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.cupcake.ui.R
import com.cupcake.ui.base.BaseAdapter


//@BindingAdapter("app:setNavigationIcon")
//fun setNavigationIcon(toolbar: Toolbar, idIcon: Int?) {
//    toolbar.setNavigationIcon(
//        idIcon.takeIf { it != null }?.let {
//            idIcon
//        } ?: R.drawable.ic_close
//    )
//
//}

//@BindingAdapter("app:showText")
//fun showText(textView: TextView, resId: Int?) {
//    textView.setText(resId.takeIf { it != null } ?: R.string.page_number_one)
//}
//
//@BindingAdapter("app:showButtonText")
//fun showButtonText(buttonView: Button, resId: Int?) {
//    buttonView.text = resId?.let(buttonView.context::getString)
//        ?: buttonView.context.getString(R.string.next)
//}

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
fun showWhenLoading(view: View, state: Boolean?) {
fun <T> showWhenLoading(view: View, state: Boolean) {
    view.isVisible=state
}

