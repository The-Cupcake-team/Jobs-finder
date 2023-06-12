package com.cupcake.ui.post_details


import androidx.lifecycle.viewModelScope
import com.cupcake.jobsfinder.domain.model.Post
import com.cupcake.jobsfinder.domain.usecase.GetPostByIdUseCase
import com.cupcake.viewmodels.base.BaseViewModel
import com.cupcake.viewmodels.post_details.state.PostDetailsUiState
import com.cupcake.viewmodels.post_details.state.PostUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostDetailsViewModel @Inject constructor(private val getPostById: GetPostByIdUseCase) :
    BaseViewModel<PostDetailsUiState>(PostDetailsUiState()) {
    private val _postDetailsUiState = MutableStateFlow(PostDetailsUiState())
    val postDetailsUiState = _postDetailsUiState.asStateFlow()

    init {
        getPost("3db5ba2c-ceb9-4cb6-8a83-001461b6ddc")
    }

    private fun getPost(id: String) {
        val coroutineExceptionHandler = CoroutineExceptionHandler { _, exception ->
            println("Handle $exception in CoroutineExceptionHandler")
            onError(exception.message.toString())
        }
        _postDetailsUiState.update { it.copy(isLoading = true, errors = emptyList()) }

        viewModelScope.launch(coroutineExceptionHandler) {
            val post = getPostById(id)
            onGetData(post)
        }


    }

    private fun onGetData(post: Post) {
        _postDetailsUiState.update {
            it.copy(
                post = PostUiState(
                    id = post.id,
                    content = post.content,
                    createdAt = "",
                )
            )
        }
    }

    private fun onError(message: String) {
        _postDetailsUiState.update { error ->
            error.copy(
                isLoading = false,
                errors = listOf(message),
            )
        }
    }


}