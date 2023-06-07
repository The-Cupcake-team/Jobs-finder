package com.cupcake.jobsfinder.ui.utill


import androidx.appcompat.widget.Toolbar
import androidx.databinding.BindingAdapter
import com.cupcake.jobsfinder.R


@BindingAdapter("app:setNavigationIcon")
fun setNavigationIcon(toolbar: Toolbar, idIcon: Int?) {

    toolbar.setNavigationIcon(
        idIcon.takeIf { it != null }?.let {
            idIcon
        } ?: R.drawable.ic_arrow_down
    );

}