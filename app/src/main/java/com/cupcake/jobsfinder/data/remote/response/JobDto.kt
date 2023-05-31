package com.cupcake.jobsfinder.data.remote.response

data class JobDto(
//    val id: Long?,
    val idJobTitle: Int?,
    val company: String?,
//    val createdTime : String?,
    val workType: String?,
    val jobLocation: String?,
    val jobDescription: String?,
    val price: String?
)
