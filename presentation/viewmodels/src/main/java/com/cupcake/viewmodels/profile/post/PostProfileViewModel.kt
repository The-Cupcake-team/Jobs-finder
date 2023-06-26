package com.cupcake.viewmodels.profile.post

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.cupcake.models.Post
import com.cupcake.usecase.GetAllUserPostUseCase
import com.cupcake.usecase.GetPostSavedByIdUseCase
import com.cupcake.viewmodels.base.BaseErrorUiState
import com.cupcake.viewmodels.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.update
import javax.inject.Inject
@HiltViewModel
class PostProfileViewModel @Inject constructor(
    private val getPostsSavedUseCase: GetPostSavedByIdUseCase,
    private val getAllUserPost:GetAllUserPostUseCase
) : BaseViewModel<PostsSavedUIState>(PostsSavedUIState()), PostProfileInterAction {
//    private val _postEvent = MutableSharedFlow<Event<PostProfileEvent>>()
//    val postEvent = _postEvent.asSharedFlow()
    init {
        getSavedPost()
        getUserPost()
    }

   private fun getSavedPost() {
        tryToExecute(
            { getPostsSavedUseCase() },
            ::onGetSavedPostSuccess,
            ::onGetPostsFailure
        )
    }
   private fun getUserPost(){
        tryToExecute(
            {getAllUserPost()},
            ::onGetUserPostsSuccess,
            ::onGetPostsFailure
        )
    }

    private fun onGetUserPostsSuccess(posts: List<Post>) {
        _state.update {
            it.copy(
                isLoading = false,
                isError = false,
                error = null,
                isSuccess = true,
                isRefresh = false,
                recentPostsResult = posts.map { post -> post.toPostItemUIState() },
            )
        }
    }
    private fun onGetSavedPostSuccess(users: List<Post>){
        _state.update {
            it.copy(
                isLoading = false,
                isError = false,
                error = null,
                isSuccess = true,
                isRefresh = false,
                savedPostsResult = users.map { users -> users.toPostItemUIState() }.reversed(),
            )
        }
    }

    private fun onGetPostsFailure(error: BaseErrorUiState) {
        _state.update {
            it.copy(
                isLoading = false,
                isError = true,
                error = error,
                isSuccess = false,
                isRefresh = false
            )
        }
    }

    private fun Post.toPostItemUIState(): ProfilePostItemUIState {
        return ProfilePostItemUIState(
            id = id ?: "",
            createdAt = createdAt ?: "",
            content = content ?: "",
            image = postImage
        )
    }


    fun onRetryClicked(){
        _state.update {it.copy(error = null, isLoading = true, isSuccess = false) }
        getSavedPost()
        getUserPost()
    }

    fun onRefreshClicked(){
        _state.update {it.copy(error = null, isRefresh = true,isSuccess = false) }
        getSavedPost()
        getUserPost()
    }

}