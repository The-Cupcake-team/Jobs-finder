package com.cupcake.remote.response.authentication.register

import kotlinx.serialization.Serializable

@Serializable
data class UserDto(
    val id : String,
    val username : String,
    val fullName : String,
    val phoneNumber : String,
    val isActive : Boolean,
    val createdAt : Long,
    val token : TokenDto
)