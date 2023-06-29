package com.cupcake.viewmodels.posts

import androidx.lifecycle.viewModelScope
import com.cupcake.models.UserProfile
import com.cupcake.usecase.ProfileUseCase
import com.cupcake.viewmodels.base.BaseViewModel
import com.cupcake.viewmodels.profile.ProfileUISate
import com.cupcake.viewmodels.utill.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class PostsViewModel @Inject constructor(private val profileUseCase: ProfileUseCase) :
    BaseViewModel<PostsUIState>(PostsUIState()),
    PostInteractionListener {
    private val _postEvent = MutableSharedFlow<Event<PostsEvent>>()
    val postEvent = _postEvent.asSharedFlow()
    init {
        viewModelScope.launch {
            getProfileData()
        }
    }
    private suspend fun getProfileData() {
        withContext(Dispatchers.IO) {
            val profile = profileUseCase().toProfileUISate()
            updateState {
                it.copy(
                    profileResult = profile,
                )
            }
        }
    }

    private fun UserProfile.toProfileUISate(): ProfileUISate {
        return ProfileUISate(
            avatar = avatar,
            linkWebsite = linkWebsite,
            location = location,
            fullName = fullName,
            JobTitle = jobTitles,
        )
    }

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

    fun onFloatingActionClickListener() {
        viewModelScope.launch {
            _postEvent.emit(Event(PostsEvent.OnFloatingActionClick))
        }
    }

}