package com.cupcake.jobsfinder.ui.utill


import android.widget.TextView
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

@BindingAdapter("app:showFormNumber")
fun showFormNumber(textView: TextView, formNumber: Int){
    if(formNumber == 0){
        textView.text = "1 of 2"
    }else{
        textView.text = "2 of 2"
    }
}