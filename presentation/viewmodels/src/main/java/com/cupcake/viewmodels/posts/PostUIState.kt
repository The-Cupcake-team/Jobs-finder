package com.cupcake.viewmodels.posts

import android.os.Parcelable
import com.cupcake.viewmodels.base.BaseErrorUiState
import com.cupcake.viewmodels.profile.ProfileUISate
import kotlinx.parcelize.Parcelize


data class PostsUIState(
    val postsResult: List<PostItemUIState> = emptyList(),
    val profileResult: ProfileUISate = ProfileUISate(),
    val isLoading: Boolean = true,
    val isError: Boolean = false,
    val error: BaseErrorUiState? = null,
    val isRefresh:Boolean=false,
    val isSuccess:Boolean=false
)

@Parcelize
data class PostItemUIState(
    val id: String = "",
    val createdAt: String = "",
    val description: String = "",
    val creatorName: String = "",
    val jobTitle: String = "",
    val profileImage: String = "",
    val likes: Int = 0,
    val isLiked: Boolean = false,
    val comments: Int = 0,
    val image: String = "",
): Parcelable




