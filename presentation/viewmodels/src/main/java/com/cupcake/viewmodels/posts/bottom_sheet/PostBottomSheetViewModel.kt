package com.cupcake.viewmodels.posts.bottom_sheet

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.cupcake.models.Post
import com.cupcake.usecase.SavePostUseCase
import com.cupcake.viewmodels.base.BaseViewModel
import com.cupcake.viewmodels.posts.PostItemUIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostBottomSheetViewModel @Inject constructor(
    private val savePostUseCase: SavePostUseCase,
    savedStateHandle: SavedStateHandle
): BaseViewModel<PostBottomSheetUiState>(PostBottomSheetUiState()), PostBottomSheetListener{

    private val _event = MutableSharedFlow<PostBottomSheetEvent>()
    val event = _event.asSharedFlow()
    var postUiState: PostItemUIState = savedStateHandle["postUiState"] ?: PostItemUIState()


    override fun onFollowCLickListener() {
        viewModelScope.launch { _event.emit(PostBottomSheetEvent.OnFollowClickListener(postUiState.id)) }
    }

    override fun onSaveListener() {
        viewModelScope.launch(Dispatchers.IO){
            savePostUseCase(postUiState.toPost())
        }
        viewModelScope.launch { _event.emit(PostBottomSheetEvent.OnSaveListener) }
    }

    fun changeSavedState(){
        viewModelScope.launch(Dispatchers.IO){
            savePostUseCase.isAlreadyExist(postUiState.id).apply {
                _state.update {
                    it.copy(isSaved = this, textSaved = if (this) "Saved" else "Save")
                }
            }
        }
    }


    fun PostItemUIState.toPost(): Post{
        return Post(
            id = id,
            createdAt = createdAt,
            content = description,
            creatorName = creatorName,
            postImage = image,
            jobTitle = jobTitle,
            profileImage = profileImage
        )
    }

}