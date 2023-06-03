package com.cupcake.jobsfinder.ui.create_job.state

data class JobFormUiState(
    val idJobTitle: Long = 0,
    val company: String = "",
    val createdTime: String = "",
    val workType: String = "",
    val jobType: String = "",
    val jobLocation: String = "",
    val jobDescription: String = "",
    val salary: String = "",
)