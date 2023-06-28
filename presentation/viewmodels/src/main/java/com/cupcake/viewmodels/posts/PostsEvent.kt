package com.cupcake.viewmodels.posts

sealed class PostsEvent {
    object OnProfileClick : PostsEvent()
    object OnSearchClick : PostsEvent()
    object OnNotificationClick : PostsEvent()

    object OnFloatingActionClick : PostsEvent()
}
