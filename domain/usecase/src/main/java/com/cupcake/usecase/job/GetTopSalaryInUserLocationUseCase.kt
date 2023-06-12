package com.cupcake.usecase.job


import repo.JobFinderRepository
import javax.inject.Inject

class GetTopSalaryInUserLocationUseCase @Inject constructor(
    private val repository: JobFinderRepository
) {
//    suspend operator fun invoke(limit: Int): List<JobWithTitle> {
//        return repository.getJobs()
//            .sortedByDescending { it.jobSalary }
//            .take(limit)
//            .map { it.toJobWithJobTitle() }
//    }
}