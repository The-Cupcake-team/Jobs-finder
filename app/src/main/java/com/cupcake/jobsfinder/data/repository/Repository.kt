package com.cupcake.jobsfinder.data.repository

import com.cupcake.jobsfinder.data.dto.JobTitleDto

interface Repository {
    suspend fun JobTitle():List<JobTitleDto>
}