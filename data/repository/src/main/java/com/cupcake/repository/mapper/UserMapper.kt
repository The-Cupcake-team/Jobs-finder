package com.cupcake.repository.mapper

import com.cupcake.models.Profile
import com.cupcake.models.Token
import com.cupcake.models.User
import com.cupcake.remote.response.authentication.register.ProfileDto
import com.cupcake.remote.response.authentication.register.TokenDto
import com.cupcake.remote.response.authentication.register.UserDto

fun UserDto.toUser(): User {
    return User(
        id = id,
        username = username,
        fullName = fullName,
        email = email,
        profile = profile.toProfile(),
        token = token.toToken()
    )
}

fun ProfileDto.toProfile(): Profile {
    return Profile(
        id = id,
        bio = bio,
        avatar = avatar,
        linkWebsite = linkWebsite,
        location = location,
        jobTitle = jobTitle.toJobTitle(
        )
    )
}

fun TokenDto.toToken(): Token {
    return Token(
        token = token,
        expireTime = expireTime,
    )
}