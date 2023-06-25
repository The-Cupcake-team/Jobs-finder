package com.cupcake.viewmodels.profile.post

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.cupcake.models.Post
import com.cupcake.usecase.GetAllUserPostUseCase
import com.cupcake.usecase.GetPostSavedByIdUseCase
import com.cupcake.viewmodels.base.BaseErrorUiState
import com.cupcake.viewmodels.base.BaseViewModel
import com.cupcake.viewmodels.posts.SpecialPostsEvent
import com.cupcake.viewmodels.utill.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class PostProfileViewModel @Inject constructor(
    private val getPostsSavedUseCase: GetPostSavedByIdUseCase,
    private val getAllUserPost:GetAllUserPostUseCase
) : BaseViewModel<PostsSavedUIState>(PostsSavedUIState()), PostProfileInterAction {
    private val _postEvent = MutableSharedFlow<Event<PostProfileEvent>>()
    val postEvent = _postEvent.asSharedFlow()
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
        Log.d("devo", " onGetPostsSuccess$posts")
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
        Log.d("devo", " onGetPostsSuccess$users")
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
        Log.d("devo", " onGetPostsSuccess$error")
    }

    private fun Post.toPostItemUIState(): ProfilePostItemUIState {
        return ProfilePostItemUIState(
            id = id ?: "",
            createdAt = createdAt ?: "",
            content = content ?: "",
            image = postImage
        )
    }

    override fun onPostSavedClick() {
    }

    override fun onPostRecentClick() {
    }


//    fun onRetryClicked() {
//        _state.update { it.copy(error = null, isLoading = true) }
//        getSavedPost()
//    }

}