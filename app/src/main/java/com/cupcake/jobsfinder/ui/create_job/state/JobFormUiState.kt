package com.cupcake.jobsfinder.ui.create_job.state

data class JobFormUiState(
    val idJobTitle: Int = 0,
    val company: String = "",
    val workType: String = "",
    val jobType: String = "",
    val jobLocation: String = "",
    val jobDescription: String = "",
    val salary: Double = 0.0,
)