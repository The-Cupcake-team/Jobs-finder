package com.cupcake.viewmodels.profile.about_me

import com.cupcake.usecase.ProfileUseCase
import com.cupcake.viewmodels.base.BaseViewModel
import com.cupcake.viewmodels.profile.ProfileUISate
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AboutMeProfileViewModel @Inject constructor(
) : BaseViewModel<ProfileUISate>(ProfileUISate()) {

}