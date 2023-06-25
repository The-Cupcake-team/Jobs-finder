package com.cupcake.viewmodels.profile

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.cupcake.models.Post
import com.cupcake.models.Profile
import com.cupcake.models.UserProfile
import com.cupcake.usecase.ProfileUseCase
import com.cupcake.usecase.validation.ValidateUsernameUseCase
import com.cupcake.viewmodels.base.BaseErrorUiState
import com.cupcake.viewmodels.base.BaseViewModel
import com.cupcake.viewmodels.comment.CommentUiState
import com.cupcake.viewmodels.jobs.JobUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val profileUseCase: ProfileUseCase,
) : BaseViewModel<ProfileUISate>(ProfileUISate()) {



    init {

        viewModelScope.launch(Dispatchers.IO) {
            getProfileData()
        }

    }

    private suspend fun getProfileData() {
        profileUseCase().toProfileUISate()
            .apply {
                updateState {
                    it.copy(
                        fullName =fullName,
                        JobTitle=JobTitle,
                        avatar=avatar,
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


}