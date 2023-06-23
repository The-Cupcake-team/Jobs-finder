package com.cupcake.remote.response.authentication.register

import com.cupcake.remote.response.JobTitleDto
import kotlinx.serialization.Serializable

@Serializable
data class ProfileDto(
    val id: String,
    val bio: String,
    val avatar: String,
    val linkWebsite: String,
    val location: String,
    val jobTitle: JobTitleDto
)
