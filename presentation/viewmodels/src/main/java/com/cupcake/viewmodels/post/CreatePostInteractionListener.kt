package com.cupcake.viewmodels.post

import com.cupcake.viewmodels.base.BaseInteractionListener

interface CreatePostInteractionListener : BaseInteractionListener {
    fun onCameraClick()
    fun onPhotoClick()
    fun onRemoveImageClick()
}