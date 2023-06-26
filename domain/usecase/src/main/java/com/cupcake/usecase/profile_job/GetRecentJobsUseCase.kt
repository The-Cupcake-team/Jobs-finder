package com.cupcake.usecase.profile_job

import com.cupcake.models.Job
import com.cupcake.models.JobSalary
import com.cupcake.models.JobTitle
import repo.JobFinderRepository
import javax.inject.Inject

class GetRecentJobsUseCase @Inject constructor(
    private val repository: JobFinderRepository
){
        suspend operator fun invoke(): List<Job>{
        return listOf(Job("12",
                  JobTitle(12),
            "zrebafarm",
            "2022",
            "220",
            "ezreba",
            "fala7",
            "zereba Education",
            JobSalary(2.3,2.5),
            "3.5"
            ,"mm",
            emptyList()
        ),Job("12",
            JobTitle(12),
            "zrebafarm",
            "2022",
            "220",
            "ezreba",
            "fala7",
            "zereba Education",
            JobSalary(2.3,2.5),
            "3.5"
            ,"mm",
            emptyList()
        ),Job("12",
            JobTitle(12),
            "zrebafarm",
            "2022",
            "220",
            "ezreba",
            "fala7",
            "zereba Education",
            JobSalary(2.3,2.5),
            "3.5"
            ,"mm",
            emptyList()
        ),Job("12",
            JobTitle(12),
            "zrebafarm",
            "2022",
            "220",
            "ezreba",
            "fala7",
            "zereba Education",
            JobSalary(2.3,2.5),
            "3.5"
            ,"mm",
            emptyList()
        )
        )

    }
}