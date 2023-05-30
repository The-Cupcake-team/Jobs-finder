package com.cupcake.jobsfinder.data.repository

import com.cupcake.jobsfinder.data.model.JobTitleDto

interface Repository {
    suspend fun getAllJobTitles():List<JobTitleDto>
}