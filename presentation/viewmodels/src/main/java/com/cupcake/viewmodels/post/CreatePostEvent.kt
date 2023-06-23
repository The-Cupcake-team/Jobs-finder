package com.cupcake.viewmodels.post

sealed class CreatePostEvent{
    object OnCameraClick:CreatePostEvent()
    object OnPhotoClick:CreatePostEvent()
    object OnPostClick:CreatePostEvent()
}
