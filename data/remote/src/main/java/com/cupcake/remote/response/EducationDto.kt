package com.cupcake.remote.response

import kotlinx.serialization.Serializable

@Serializable
data class EducationDto(
    val id: String?,
    val userId: String?,
    val degree: String?,
    val school: String?,
    val city: String?,
    val date: DateDto?
)