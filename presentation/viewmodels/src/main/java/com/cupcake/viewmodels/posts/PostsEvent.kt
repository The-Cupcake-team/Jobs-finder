package com.cupcake.viewmodels.posts

sealed class PostsEvent{
    data class PostCommentClick(val id: String): PostsEvent()
    data class PostShareClick(val id: String): PostsEvent()
   data class PostOptionsClick(val model: PostItemUIState): PostsEvent()
}
