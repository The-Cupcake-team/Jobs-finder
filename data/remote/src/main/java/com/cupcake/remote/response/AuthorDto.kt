package com.cupcake.remote.response


import kotlinx.serialization.Serializable

@Serializable
data class AuthorDto(
    val id: String?,
    val name: String?,
    val jobTitle: String?,
    val avtar: String?
)