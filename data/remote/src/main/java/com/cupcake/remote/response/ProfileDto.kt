package com.cupcake.remote.response


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