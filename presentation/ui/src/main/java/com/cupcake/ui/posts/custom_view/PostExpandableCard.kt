package com.cupcake.ui.posts.custom_view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import com.cupcake.ui.databinding.PostContentExpandBinding


class PostExpandableCard @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : LinearLayout(context, attrs, defStyleAttr) {
    private val binding: PostContentExpandBinding = PostContentExpandBinding.inflate(
        LayoutInflater.from(context),
        this,
        true
    )

    init {
        textViewContentPostFirstTextClick()
        textViewContentPostFullTextClick()
    }


    fun postStartText(startValue: String) {

        binding.textViewContentPostFirstText.text = startValue
    }

    fun postFullText(fullValue: String) {
        binding.textViewContentPostFullText.text = fullValue
    }

    private fun textViewContentPostFullTextClick() {
        binding.apply {
            textViewContentPostFullText.setOnClickListener {
                textViewContentPostFullText.visibility = View.GONE
                textViewContentPostFirstText.visibility = View.VISIBLE
            }
        }
    }
    private fun textViewContentPostFirstTextClick() {
        binding.apply {
            textViewContentPostFirstText.setOnClickListener {
                textViewContentPostFirstText.visibility = View.GONE
                textViewContentPostFullText.visibility = View.VISIBLE
            }
        }
    }

}



