package com.cupcake.jobsfinder.data.repository

import com.cupcake.jobsfinder.data.dto.JobTitleDto

class RepositoryImpl : Repository {
    override suspend fun getAllJobTitles(): List<JobTitleDto> {
        return listOf(
            JobTitleDto(
                id = "ID HERE",
                title = "Android"
            )
        )
    }
}