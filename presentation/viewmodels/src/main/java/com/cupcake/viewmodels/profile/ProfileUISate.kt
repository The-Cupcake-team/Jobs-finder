package com.cupcake.viewmodels.profile


data class ProfileUISate (
    val  bio : Bio=Bio(),
    val  avatar : String="",
    val  cover : String="",

    )

data class Bio (
    val  fullName : String="",
    val  location : String="",
    val  jobTitle : JobTitle = JobTitle(),
    val  linkWebsite : String="",



    )
data class JobTitle(
    val id: Int=1,
    val title: String=""
)