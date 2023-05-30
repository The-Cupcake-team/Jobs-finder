package com.cupcake.jobsfinder.data.repository

import com.cupcake.jobsfinder.data.model.JobTitleDto

interface Repository {
    suspend fun JobTitle():List<JobTitleDto>
}