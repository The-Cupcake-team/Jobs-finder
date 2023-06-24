package com.cupcake.viewmodels.profile

import com.cupcake.usecase.login.LoginUseCase
import com.cupcake.usecase.register.RegisterUseCase
import com.cupcake.viewmodels.base.BaseViewModel
import javax.inject.Inject

class ProfileViewModel@Inject constructor(

    ) : BaseViewModel<ProfileUISate>(ProfileUISate()) {

}