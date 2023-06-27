package com.cupcake.viewmodels.profile

import com.cupcake.viewmodels.profile.EducationUiState
import com.cupcake.viewmodels.profile.resume.skill.SkillsUiState

data class ResumeUiState (
    var educationUiState: List<EducationUiState> = emptyList(),
    var sillsUiState: List<SkillsUiState> = emptyList(),

    )