package com.cupcake.viewmodels.profile

import androidx.lifecycle.viewModelScope
import com.cupcake.models.UserProfile
import com.cupcake.usecase.profile.ProfileUseCase
import com.cupcake.viewmodels.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
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