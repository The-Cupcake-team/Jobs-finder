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
                filterJobTitle(job, searchTitle)
                        || filterBySalaryRange(job, salaryRange)
                        || filterByLocation(job, location)
                        || filterByJobType(job, jobType)
                        || filterByWorkType(job, workType)
                        || filterByExperienceLevel(job, experienceLevel)

            }
        return filteredJobs
    }


    private fun filterByLocation(job: Job, location: String): Boolean {
        if(location.isNotEmpty()){
            return location?.let { job.jobLocation.contains(it, ignoreCase = true) } ?: true
        }
        return false
    }

    private fun filterByJobType(job: Job, jobType: String): Boolean {
        if (jobType.isNotEmpty()){
            return jobType?.let { job.jobType.equals(it, ignoreCase = true) } ?: true
        }
        return false
    }

    private fun filterByWorkType(job: Job, workType: String): Boolean {
        if(workType.isNotEmpty()){
            return workType?.let { job.workType.equals(it, ignoreCase = true) } ?: true
        }
        return false
    }

    private fun filterByExperienceLevel(job: Job, experienceLevel: String): Boolean {
        if(experienceLevel.isNotEmpty()){
            return experienceLevel?.let { job.jobExperience.equals(it, ignoreCase = true) } ?: true
        }
        return false
    }

    private fun filterBySalaryRange(job: Job, salaryRange: Pair<Double, Double>): Boolean {
       if(salaryRange.first > 0.0 && salaryRange.second > 0.0){
           return salaryRange?.let { (minSalary, maxSalary) ->
               job.jobSalary.minSalary >= minSalary && job.jobSalary.maxSalary <= maxSalary
           } ?: true
       }
        return false
    }

    private fun filterJobTitle(job: Job, searchTitle: String): Boolean{
        if(searchTitle.isNotEmpty()){
            return job.jobTitle.title?.contains(searchTitle) ?: true
        }
        return false
    }

}

