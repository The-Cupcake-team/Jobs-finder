package com.cupcake.viewmodels.profile.resume

import com.cupcake.viewmodels.profile.EducationUiState

data class ResumeUiState (
    var educationUiState: List<EducationUiState> = emptyList(),
    var sillsUiState: List<SkillsUiState> = emptyList(),

    )