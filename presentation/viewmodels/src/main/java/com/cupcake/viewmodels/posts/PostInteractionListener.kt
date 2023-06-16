package com.cupcake.viewmodels.posts

import com.cupcake.viewmodels.base.BaseInteractionListener

interface PostInteractionListener: BaseInteractionListener {

    fun onCommentClick(id: String)
}