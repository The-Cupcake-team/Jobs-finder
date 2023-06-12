//package com.cupcake.usecase.job
//
//import com.cupcake.jobsfinder.domain.mapper.toJobWithJobTitle
//import com.cupcake.jobsfinder.domain.model.JobWithTitle
//import com.cupcake.repository.JobFinderRepository
//import javax.inject.Inject
//
//class GetTopSalaryInUserLocationUseCase @Inject constructor(
//    private val repository: com.cupcake.repository.JobFinderRepository
//) {
//    suspend operator fun invoke(limit: Int): List<JobWithTitle> {
//        return repository.getJobs()
//            .sortedByDescending { it.jobSalary }
//            .take(limit)
//            .map { it.toJobWithJobTitle() }
//    }
//}