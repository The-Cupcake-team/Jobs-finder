package com.cupcake.models

data class UserProfile (
    val id: String,
    var avatar: String,
    var fullName: String,
    var jobTitles: String,
    var location: String,
    var linkWebsite: String,
)