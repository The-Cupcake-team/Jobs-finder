package com.cupcake.remote.response

import kotlinx.serialization.Serializable

@Serializable
data class DateDto(
    val start: String?,
    val end: String?
)
