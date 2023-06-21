package com.cupcake.ui.utill


import android.graphics.drawable.Drawable
import android.os.Build
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import coil.load
import com.cupcake.ui.R
import com.cupcake.ui.base.BaseAdapter
import com.cupcake.viewmodels.base.BaseErrorUiState
import com.cupcake.viewmodels.posts.PostItemUIState


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

@BindingAdapter(value = ["app:showWhenIf"])
fun showWhenIf(view: View, state: Boolean?) {
    view.isVisible=state ?: false
}

@BindingAdapter(value = ["app:showDrawableErrorImage"])
fun showDrawableErrorImage(view: ImageView, errorType: BaseErrorUiState?){
    when(errorType){
        is BaseErrorUiState.Disconnected -> view.setImageResource(R.drawable.network_connection_failure_backup)
        is BaseErrorUiState.NoFoundError -> view.setImageResource(R.drawable.no_results_3x)
        is BaseErrorUiState.ServerError -> view.setImageResource(R.drawable.no_content_backup)
        is BaseErrorUiState.UnAuthorized -> view.setImageResource(R.drawable.no_content_backup)
        else -> {view.setImageResource(R.drawable.no_content_backup)}
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@BindingAdapter(value = ["app:convertTime"])
fun convertTime(view: TextView , time: Long){
   view.text = convert(time)
}

@BindingAdapter("setIsSavedIcon")
fun setIsSavedIcon(textView: TextView, isSaved: Boolean?) {
    val drawable: Drawable? = if (isSaved == true) {
        ContextCompat.getDrawable(textView.context, R.drawable.ic_saved)
    } else {
        ContextCompat.getDrawable(textView.context, R.drawable.ic_save)
    }
    textView.setCompoundDrawablesWithIntrinsicBounds(drawable, null, null, null)
}

@BindingAdapter("dismissSwipeRefreshIf")
fun dismissSwipeRefreshIf(swipeRefreshLayout: SwipeRefreshLayout, dismiss: Boolean) {
    swipeRefreshLayout.isRefreshing = dismiss
}


@BindingAdapter(value = ["app:isLiked"])
fun isLiked(view: ImageView , isLiked: Boolean){
    if (isLiked){
        view.setImageResource(R.drawable.selected_thumb)
    }
    else{
        view.setImageResource(R.drawable.tabler_thumb)
    }
}

@BindingAdapter(value = ["app:isRefresh"])
fun isRefresh(view: SwipeRefreshLayout , isRefresh: Boolean){
    view.isRefreshing = isRefresh
}



