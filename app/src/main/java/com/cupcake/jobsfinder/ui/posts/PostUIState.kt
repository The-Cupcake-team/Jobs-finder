package com.cupcake.jobsfinder.ui.posts

data class PostsUIState(
    val postsResult: List<PostItemUIState> = emptyList(),
    val isLoading: Boolean = false,
    val errors: List<ErrorUIState> = emptyList(),
)

data class PostItemUIState(
    val id: String,
    val postInteraction: PostInteraction,
    val username: String = "",
    val jobTitle: String = "",
    val createdAt: Long,
    val description: String,
    val imageUrl: String = "",
    val likesCount: Int = 0,
    val commentCount: Int = 0
)

data class PostInteraction(
    val isFollowed: Boolean = false,
    val isLiked: Boolean = false,
    val isSaved: Boolean = false,
)

data class ErrorUIState(
    val message: String
)