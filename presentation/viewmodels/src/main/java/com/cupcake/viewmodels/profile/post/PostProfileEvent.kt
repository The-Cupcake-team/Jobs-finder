package com.cupcake.viewmodels.profile.post


sealed class PostProfileEvent{
 data class PostCardClick(val id: String): PostProfileEvent()
   object OnProfileClick: PostProfileEvent()
//    data class PostCommentClick(val id: String): PostProfileEvent()
//    data class PostShareClick(val id: String): PostProfileEvent()
//    data class PostOptionsClick(val model: PostItemUIState): PostProfileEvent()

}
