package com.cupcake.remote.response

import kotlinx.serialization.Serializable

@Serializable
data class Author(
    val avtar: String?,
    val id: String?,
    val jobTitle: String?,
    val name: String?
)