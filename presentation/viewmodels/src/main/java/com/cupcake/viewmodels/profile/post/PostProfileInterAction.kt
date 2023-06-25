package com.cupcake.viewmodels.profile.post

import com.cupcake.viewmodels.base.BaseInteractionListener

interface PostProfileInterAction : BaseInteractionListener {
    fun onPostSavedClick()
    fun onPostRecentClick()
}