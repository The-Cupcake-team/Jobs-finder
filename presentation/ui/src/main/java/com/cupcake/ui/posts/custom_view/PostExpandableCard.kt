package com.cupcake.ui.posts.custom_view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import com.cupcake.ui.databinding.ReadMoreLayoutBinding

class PostExpandableCard @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : LinearLayout(context, attrs, defStyleAttr) {
    private val binding: ReadMoreLayoutBinding = ReadMoreLayoutBinding.inflate(
        LayoutInflater.from(context),
        this,
        true
    )

    init {
        cardPostExpandReadMore()
        cardPostExpandReadLess()
    }


    fun postStartText(startValue: String) {

        binding.textViewContentPostBeforeReadMore.text = startValue
    }

    fun postFullText(fullValue: String) {
        binding.textViewContentPostAfterReadMore.text = fullValue
    }

    private fun cardPostExpandReadMore() {
        binding.textViewClickReadMore.setOnClickListener {
            binding.apply {
                textViewContentPostBeforeReadMore.visibility = View.GONE
                textViewClickReadMore.visibility = View.GONE
                textViewContentPostAfterReadMore.visibility = View.VISIBLE
                textViewClickReadLess.visibility = View.VISIBLE
            }
        }
    }

    private fun cardPostExpandReadLess() {
        binding.textViewClickReadLess.setOnClickListener {
            binding.apply {
                textViewContentPostAfterReadMore.visibility = View.GONE
                textViewClickReadLess.visibility = View.GONE
                textViewContentPostBeforeReadMore.visibility = View.VISIBLE
                textViewClickReadMore.visibility = View.VISIBLE
            }
        }
    }

}



