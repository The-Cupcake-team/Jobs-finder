package com.cupcake.models

data class Profile(
    val id: String,
    val bio: String,
    val avatar: String,
    val linkWebsite: String,
    val location: String,
    val jobTitle: JobTitle
)