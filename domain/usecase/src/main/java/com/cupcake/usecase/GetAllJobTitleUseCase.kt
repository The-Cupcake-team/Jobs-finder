package com.cupcake.usecase


import com.cupcake.models.JobTitle
import repo.JobFinderRepository
import javax.inject.Inject

class GetAllJobTitleUseCase @Inject constructor(
    private val jobTitleRepository: JobFinderRepository,
) {
    suspend operator fun invoke(keyword: String, limit: Int = 3): List<JobTitle> {
        return jobTitleRepository
            .getAllJobTitles()
            .asSequence()
            .sortedBy { it.title }
            .filter { it.title.startsWith(keyword, ignoreCase = true) }
            .take(limit)
            .toList()
    }
}
