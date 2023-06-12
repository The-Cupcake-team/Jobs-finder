//package com.cupcake.usecase.job
//
//import com.cupcake.models.JobWithTitle
//import javax.inject.Inject
//
//
//class GetRecommendedJobsUseCase @Inject constructor(
//    private val repository: JobFinderRepository
//) {
//    suspend operator fun invoke(limit: Int): List<JobWithTitle> {
//        return repository.getJobs()
//            .filter { it.jobTitle.title == "Android" }
//            .take(limit)
//            .map { it.toJobWithJobTitle() }
//    }
//}