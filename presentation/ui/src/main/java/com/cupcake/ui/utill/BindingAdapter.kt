package com.cupcake.ui.utill


import android.graphics.drawable.Drawable
import android.os.Build
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
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
import com.cupcake.ui.job_search.OnRangeSliderValueChangeListener
import com.cupcake.ui.utill.SalaryFormatter.Companion.formatSalary
import com.cupcake.viewmodels.base.BaseErrorUiState
import com.cupcake.viewmodels.jobs.JobTitleUiState
import com.cupcake.viewmodels.jobs.JobUiState
import com.google.android.material.slider.RangeSlider


@BindingAdapter(value = ["app:loadImage"])
fun loadImageCoil(image: ImageView, url: String?) {
    if ( url!!.isEmpty() ){
        image.visibility = View.GONE
    }else {
        image.load(url)
        image.visibility = View.VISIBLE
    }
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
    view.isVisible = state ?: false
}

@BindingAdapter(value = ["app:showDrawableErrorImage"])
fun showDrawableErrorImage(view: ImageView, errorType: BaseErrorUiState?) {
    when (errorType) {
        is BaseErrorUiState.Disconnected -> view.setImageResource(R.drawable.network_connection_failure_backup)
        is BaseErrorUiState.NoFoundError -> view.setImageResource(R.drawable.no_results_3x)
        is BaseErrorUiState.ServerError -> view.setImageResource(R.drawable.no_content_backup)
        is BaseErrorUiState.UnAuthorized -> view.setImageResource(R.drawable.no_content_backup)
        else -> {
            view.setImageResource(R.drawable.no_content_backup)
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@BindingAdapter(value = ["app:convertTime"])
fun convertTime(view: TextView, time: String?) {
    view.text = time?.let { convert(it) }
}

@BindingAdapter("scrollToPosition")
fun scrollToPosition(recyclerView: RecyclerView, position: Int) {
    recyclerView.scrollToPosition(position)
}


@BindingAdapter(value = ["app:bindArrayAdapter"])
fun bindArrayAdapter(view: AutoCompleteTextView, queryList: List<JobTitleUiState>?) {
    queryList?.let {
        val historySearchAdapter = ArrayAdapter(
            view.context,
            R.layout.item_job_title,
            it.map { jobTitle -> jobTitle.title })
        view.setAdapter(historySearchAdapter)

        view.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus && it.isNotEmpty()) {
                view.showDropDown()
            }
        }
    }
}


@BindingAdapter("app:setIconActionLeftToolBar")
fun setIconAction(view: ImageButton, state: Int?) {
    state?.let {
        it.takeIf { it == 1 }?.let {
            view.setImageResource(R.drawable.ic_close)
        } ?: view.setImageResource(R.drawable.ic_back)
    } ?: view.setImageResource(R.drawable.ic_close)

}

@BindingAdapter(value = ["app:buttonText", "app:isLoading"], requireAll = true)
fun hideTextOnLoading(button: Button, text: String, state: Boolean) {
    if (state) {
        button.text = ""
    } else
        button.text = text
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

@BindingAdapter(value = ["app:showImage"])
fun showImage(view: ImageView , imageData: Any?){
    if (imageData != null){
        view.visibility = View.VISIBLE
        view.load(imageData) {
            crossfade(true)
            crossfade(1000)
        }
    }
    else{
        view.setImageDrawable(null)
    }
}

@BindingAdapter("dateString")
fun TextView.setFormattedDate(dateString: String?) {
    val formattedDate = if (!dateString.isNullOrBlank()) {
        val parts = dateString.split(".")
        if (parts.size == 3) {
            val month = when (parts[0].toIntOrNull()) {
                1 -> "Jan"
                2 -> "Feb"
                3 -> "Mar"
                4 -> "Apr"
                5 -> "May"
                6 -> "Jun"
                7 -> "Jul"
                8 -> "Aug"
                9 -> "Sep"
                10 -> "Oct"
                11 -> "Nov"
                12 -> "Dec"
                else -> ""
            }
            val year = parts[2]
            "$month $year"
        } else {
            ""
        }
    } else {
        ""
    }

    text = formattedDate
}

@BindingAdapter("onValueChange")
fun setRangeSliderOnValueChangeListener(
    slider: RangeSlider,
    onValueChange: OnRangeSliderValueChangeListener
) {
    slider.addOnSliderTouchListener(object : RangeSlider.OnSliderTouchListener {
        override fun onStartTrackingTouch(slider: RangeSlider) {}

        override fun onStopTrackingTouch(slider: RangeSlider) {
            val min = slider.values[0]
            val max = slider.values[1]
            onValueChange.onValueChange(min, max)
        }
    })
}



@BindingAdapter("salaryRange")
fun setSalaryRange(textView: TextView, item: JobUiState) {
    val formattedSalaryRange = formatSalary(minSalary = item.minSalary, maxSalary = item.maxSalary)
    textView.text = formattedSalaryRange
}

@BindingAdapter("enableOnClickIfTextNotEmpty")
fun enableOnClickIfTextNotEmpty(view: TextView, editText: EditText) {
    editText.addTextChangedListener(object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            view.isEnabled = !s.isNullOrEmpty()
        }

        override fun afterTextChanged(s: Editable?) {}
    })
}



