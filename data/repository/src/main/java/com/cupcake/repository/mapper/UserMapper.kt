package com.cupcake.repository.mapper

import com.cupcake.models.Token
import com.cupcake.models.User
import com.cupcake.remote.response.authentication.register.TokenDto
import com.cupcake.remote.response.authentication.register.UserDto

fun UserDto.toUser(): User {
    return User(
        id = id,
        username = username,
        fullName = fullName,
        email = phoneNumber,
        isActive = isActive,
        createdAt = createdAt,
        token = Token(
            token = token.token,
            expireTime = token.expireTime
        )
    )
}

fun TokenDto.toToken(): Token {
    return Token(
        token = token,
        expireTime = expireTime
    )
}