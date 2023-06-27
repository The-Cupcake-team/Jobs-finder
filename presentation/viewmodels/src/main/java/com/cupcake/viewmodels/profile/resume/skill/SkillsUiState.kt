package com.cupcake.viewmodels.profile.resume.skill

import android.os.Parcelable
import com.cupcake.models.Skill
import kotlinx.parcelize.Parcelize

@Parcelize
data class SkillsUiState (
    var skill: String = "",
    val id: String="",
    val userId: String="",
) : Parcelable


fun Skill.toSkillsUiState(): SkillsUiState {
    return SkillsUiState(
        skill=skill?: "",
        id=id?:"",
        userId=userId?:""

    )
}