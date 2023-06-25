package com.cupcake.repository.mapper

import com.cupcake.jobsfinder.local.entities.ProfileEntity
import com.cupcake.models.JobTitle
import com.cupcake.models.Profile
import com.cupcake.models.Token
import com.cupcake.models.User
import com.cupcake.models.UserProfile
import com.cupcake.remote.response.ProfileDto
import com.cupcake.remote.response.authentication.register.TokenDto
import com.cupcake.remote.response.authentication.register.UserDto

fun UserDto.toUser(): User {
    return User(
        id = id,
        username = username,
        fullName = fullName,
        email = email,
        profile = profile.toProfile(),
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

fun ProfileDto.toProfile(): Profile {
    return Profile(
        id = id,
        bio = bio,
        avatar = avatar,
        linkWebsite = linkWebsite,
        location = location,
        jobTitle = jobTitle.toJobTitle()
    )
}

fun User.toProfileEntity(): ProfileEntity {
    return ProfileEntity(
        id = id,
        fullName = fullName,
        avatar = profile.avatar,
        jobTitles = profile.jobTitle.title,
        linkWebsite = profile.linkWebsite,
        location = profile.location
    )
}
fun ProfileEntity.toProfile():UserProfile {
    return  UserProfile(
        id=id,
        avatar=avatar,
        location = location,
        linkWebsite = linkWebsite,
        fullName = fullName,
        jobTitles = jobTitles,
    )
}


