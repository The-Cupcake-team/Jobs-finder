package com.cupcake.viewmodels.posts.bottom_sheet

sealed class PostBottomSheetEvent{

    data class OnFollowClickListener(val id: String): PostBottomSheetEvent()

    object OnSaveListener: PostBottomSheetEvent()
}
