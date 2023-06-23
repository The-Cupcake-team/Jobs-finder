package com.cupcake.usecase

import com.cupcake.models.Job
import repo.JobFinderRepository
import javax.inject.Inject

class JobSearchFilterUseCase@Inject constructor(
    private val repository: JobFinderRepository
){

    suspend operator fun invoke(
        searchTitle: String = "",
        location: String = "",
        jobType: String = "",
        workType: String = "",
        experienceLevel: String = "",
        salaryRange: Pair<Double, Double> = Pair(0.0,0.0)
    ): List<Job> {
        val allJobs = repository.getJobs()

        val filteredJobs = allJobs
            .filter { job ->
                filterJobTitle(job, searchTitle) &&
                filterByLocation(job, location) &&
                        filterByJobType(job, jobType) &&
                        filterByWorkType(job, workType) &&
                        filterByExperienceLevel(job, experienceLevel) &&
                        filterBySalaryRange(job, salaryRange)
            }
        return filteredJobs
    }


    private fun filterByLocation(job: Job, location: String?): Boolean {
        return location?.let { job.jobLocation.contains(it, ignoreCase = true) } ?: true
    }

    private fun filterByJobType(job: Job, jobType: String?): Boolean {
        return jobType?.let { job.jobType.equals(it, ignoreCase = true) } ?: true
    }

    private fun filterByWorkType(job: Job, workType: String?): Boolean {
        return workType?.let { job.workType.equals(it, ignoreCase = true) } ?: true
    }

    private fun filterByExperienceLevel(job: Job, experienceLevel: String?): Boolean {
        return experienceLevel?.let { job.jobExperience.equals(it, ignoreCase = true) } ?: true
    }

    private fun filterBySalaryRange(job: Job, salaryRange: Pair<Double, Double>?): Boolean {
        return salaryRange?.let { (minSalary, maxSalary) ->
            job.jobSalary.minSalary >= minSalary && job.jobSalary.maxSalary <= maxSalary
        } ?: true
    }

    private fun filterJobTitle(job: Job, searchTitle: String): Boolean{
        return job.jobTitle.title?.contains(searchTitle)!!
    }

}

