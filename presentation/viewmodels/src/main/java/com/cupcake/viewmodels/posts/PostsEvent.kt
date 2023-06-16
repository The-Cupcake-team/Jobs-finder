package com.cupcake.viewmodels.posts

sealed class PostsEvent{
    data class PostCommentClick(val id: String): PostsEvent()
}
