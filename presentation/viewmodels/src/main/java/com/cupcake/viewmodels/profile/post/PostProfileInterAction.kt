package com.cupcake.viewmodels.profile.post

import com.cupcake.viewmodels.base.BaseInteractionListener

interface PostProfileInterAction : BaseInteractionListener {

    fun onLikeRecentPostClick(id: String)
    fun onLikeSavedPostClick(id: String)
    fun onCardClick(id: String)
}