package com.cupcake.remote.response


import kotlinx.serialization.Serializable

@Serializable
data class ImageDto(
    val id: String?,
    val imageUrl: String?
)