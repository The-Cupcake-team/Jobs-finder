package com.cupcake.usecase

import com.cupcake.models.Job
import repo.JobFinderRepository
import javax.inject.Inject

class SaveJobUseCase @Inject constructor(
    private val repository: JobFinderRepository,
) {
    suspend operator fun invoke(job: Job): Boolean {
        if(isAlreadyExist(job.id)){
            deleteInsertedJob(job)
            return false //that mean the saved job are deleted
        }
        repository.insertJob(job)
        return true //that mean the job is saved
    }

     suspend fun isAlreadyExist(id: String): Boolean{
        repository.getSavedJobById(id) ?: return false
        return true
    }

    private suspend fun deleteInsertedJob(job: Job){
        repository.deleteJob(job)
    }
}