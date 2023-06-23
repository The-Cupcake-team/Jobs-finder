package com.cupcake.viewmodels.posts

import androidx.lifecycle.viewModelScope
import com.cupcake.viewmodels.base.BaseViewModel
import com.cupcake.viewmodels.utill.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostsViewModel  @Inject constructor() : BaseViewModel<PostsUIState>(PostsUIState()),PostInteractionListener
{
    private val _postEvent = MutableSharedFlow<Event<PostsEvent>>()
    val postEvent = _postEvent.asSharedFlow()

    override fun onProfileClick() {
        viewModelScope.launch {
            _postEvent.emit(Event(PostsEvent.OnProfileClick))
        }
    }

    override fun onSearchClick() {
        viewModelScope.launch {
            _postEvent.emit(Event(PostsEvent.OnSearchClick))
        }
    }

    override fun onNotificationClick() {
        viewModelScope.launch {
            _postEvent.emit(Event(PostsEvent.OnNotificationClick))
        }
    }

}