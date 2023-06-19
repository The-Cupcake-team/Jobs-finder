package com.cupcake.usecase

import com.cupcake.models.JobWithTitle
import repo.JobFinderRepository
import javax.inject.Inject

class SaveJobUseCase @Inject constructor(
    private val repository: JobFinderRepository,
) {
    suspend operator fun invoke(job: JobWithTitle): Boolean {
        if(isAlreadyExist(job.id)){
            deleteInsertedJob(job)
            return false //that mean the saved job are deleted
        }
        repository.insertJob(job)
        return true //that mean the job is saved
    }

    private suspend fun isAlreadyExist(id: String): Boolean{
        repository.getSavedJobById(id) ?: return false
        return true
    }

    private suspend fun deleteInsertedJob(job: JobWithTitle){
        repository.deleteJob(job)
    }
}