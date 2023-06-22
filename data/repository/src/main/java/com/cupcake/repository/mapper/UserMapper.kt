package com.cupcake.repository.mapper

import com.cupcake.remote.response.authentication.register.UserDto


fun UserDto.toUser(): com.cupcake.models.User {
    return com.cupcake.models.User(
        id = id,
        username = username,
        fullName = fullName,
        email = phoneNumber,
        isActive = isActive,
        createdAt = createdAt,
        token = token.token
    )
}