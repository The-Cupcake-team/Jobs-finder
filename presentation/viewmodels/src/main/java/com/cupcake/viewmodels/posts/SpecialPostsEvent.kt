package com.cupcake.viewmodels.posts

sealed class SpecialPostsEvent{
    data class PostCommentClick(val id: String): SpecialPostsEvent()
    data class PostShareClick(val id: String): SpecialPostsEvent()
   data class PostOptionsClick(val model: PostItemUIState): SpecialPostsEvent()
}
