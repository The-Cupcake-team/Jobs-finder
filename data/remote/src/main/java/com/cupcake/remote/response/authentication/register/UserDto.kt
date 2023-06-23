package com.cupcake.remote.response.authentication.register

import com.cupcake.remote.response.ProfileDto
import kotlinx.serialization.Serializable

@Serializable
data class UserDto(
    val id: String,
    val username: String,
    val fullName: String,
    val email: String,
    val profile: ProfileDto,
    val token: TokenDto,
    val isActive: Boolean,
    val createdAt: String
)