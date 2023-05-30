package com.cupcake.jobsfinder.data.repository

import com.cupcake.jobsfinder.data.dto.JobTitleDto

class RepositoryImpl : Repository {
    override suspend fun JobTitle(): List<JobTitleDto> {
        return listOf(
            JobTitleDto(
                id = 0,
                idJobTitle = 0,
                companyName = "ali and hasan",
                createdAt = "2015-5-1",
                workType = "full time",
                location = "remote",
                jobType = "full time",
                jobDescription = "android",
                salary = 1000.00
            )
        )
    }
}