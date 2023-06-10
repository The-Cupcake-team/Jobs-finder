package com.cupcake.jobsfinder.ui.utill


import android.widget.Button
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.databinding.BindingAdapter
import com.cupcake.jobsfinder.R


@BindingAdapter("app:setNavigationIcon")
fun setNavigationIcon(toolbar: Toolbar, idIcon: Int?) {
    toolbar.setNavigationIcon(
        idIcon.takeIf { it != null }?.let {
            idIcon
        } ?: R.drawable.ic_close
    )

}

@BindingAdapter("app:showText")
fun showText(textView: TextView, resId: Int?) {
    textView.setText(resId.takeIf { it != null } ?: R.string.page_number_one)
}

@BindingAdapter("app:showButtonText")
fun showButtonText(buttonView: Button, resId: Int?) {
    buttonView.text = resId?.let(buttonView.context::getString)
        ?: buttonView.context.getString(R.string.next)
}

