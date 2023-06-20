package com.cupcake.ui.posts.custom_view

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.transition.AutoTransition
import androidx.transition.TransitionManager
import com.cupcake.ui.databinding.PostExpandableCardBinding

class PostExpandableCard@JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
): ConstraintLayout(context, attrs, defStyleAttr){
    private val binding: PostExpandableCardBinding = PostExpandableCardBinding.inflate(
        LayoutInflater.from(context),
        this,
        true
    )


    init {
        cardPostExpand()
    }


    fun postStartText(startValue: String){
        binding.postFullText.text = startValue
        Log.d("lolo", "postStartText: $startValue ")
    }

    fun postFullText(fullValue: String){
        binding.postFullText.text = fullValue
    }



    private fun cardPostExpand(){
        binding.Readmore.setOnClickListener {
            binding.apply {
                if (postFullText.visibility == View.GONE){
                    TransitionManager.beginDelayedTransition(
                        cardViewPostExpandable,
                        AutoTransition()
                    )
                    postFullText.visibility = View.VISIBLE
                    postStartText.visibility =View.GONE
                }else{
                    postFullText.visibility = View.GONE
                    postStartText.visibility =View.VISIBLE
                }
            }
        }
    }

}



