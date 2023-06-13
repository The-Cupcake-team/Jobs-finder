package com.cupcake.ui.posts

data class PostsUIState(
    val postsResult: List<PostItemUIState> = emptyList(),
    val isLoading: Boolean = true,
    val errors: List<String> = emptyList(),
)

data class PostItemUIState(
    val id: String,
    val createdAt: Long,
    val description: String,
)


//fun Post.toPostItemUIState(): PostItemUIState {
//    return PostItemUIState(
//        id = id,
//        createdAt = createdAt,
//        description = content
//    )
//}