package com.cupcake.viewmodels.post_details


import com.cupcake.models.Post
import com.cupcake.usecase.GetPostByIdUseCase
import com.cupcake.viewmodels.base.BaseErrorUiState
import com.cupcake.viewmodels.base.BaseViewModel
import com.cupcake.viewmodels.post_details.state.PostDetailsUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
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

        _postDetailsUiState.update { it.copy(isLoading = true, error = null) }

        tryToExecute({
            getPostById(id)
        }, ::onGetData, ::onError)


    }

    private fun onGetData(post: Post) {
        _postDetailsUiState.update {
            it.copy(
                post = PostDetailsUiState.PostUiState(
                    id = post.id,
                    content = post.content,
                    createdAt = "",
                )
            )
        }
    }

    private fun onError(error: BaseErrorUiState) {
        _postDetailsUiState.update {
            it.copy(
                isLoading = false,
                error = error,
            )
        }
    }


}