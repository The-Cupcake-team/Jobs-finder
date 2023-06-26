package com.cupcake.viewmodels.profile.resume

import androidx.lifecycle.viewModelScope
import com.cupcake.usecase.profile.ResumeUseCase
import com.cupcake.viewmodels.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class ProfileResumeViewModel @Inject constructor(
    private val resumeUseCase : ResumeUseCase
) : BaseViewModel<ResumeUiState>(ResumeUiState()){
    init {
        viewModelScope.launch(Dispatchers.IO) {
            resumeUseCase.getAllEducations()
        }
    }
}