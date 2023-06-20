package com.cupcake.remote.response.job

import kotlinx.serialization.Serializable

@Serializable
data class JobSalaryDto(
    val minSalary: Double?,
    val maxSalary: Double?
)
