package com.cupcake.remote.response

import kotlinx.serialization.Serializable

@Serializable
data class JobSalaryDto(val minSalary: Double, val maxSalary:Double)