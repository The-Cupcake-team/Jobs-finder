package com.cupcake.viewmodels.profile.resume

data class ResumeUiState (
    var educationUiState: EducationUiState = EducationUiState(),
    var sillsUiState: SkillsUiState = SkillsUiState(),

    )