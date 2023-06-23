package com.cupcake.remote.response


import kotlinx.serialization.Serializable

@Serializable
data class Image(
    val id: String?,
    val imageUrl: String?
)