package com.cupcake.viewmodels.profile

import com.cupcake.viewmodels.base.BaseErrorUiState


data class ProfileUISate (
    val  fullName : String="",
    val  location : String="",
    val JobTitle: String="",
    val  linkWebsite : String="",
    val  avatar : String="",
    val  cover : String="",

    )

//data class Bio (
//    val  fullName : String="",
//    val  location : String="",
//    val  jobTitle : JobTitle = JobTitle(),
//    val  linkWebsite : String="",
//
//
//
//    )
//data class JobTitle(
//    val id: Int=1,
//    val title: String=""
//)