package com.cupcake.usecase

import com.cupcake.models.JobWithTitle
import repo.JobFinderRepository
import javax.inject.Inject

class SaveJobUseCase @Inject constructor(
    private val repository: JobFinderRepository,
) {
    suspend fun invoke(job: JobWithTitle): Boolean {
        if(isAlreadyExist("")){
            deleteInsertedJob("")
            return false //that mean the job deleted
        }
        repository.insertJob(job)
        return true //that mean the job is inserted
    }

    private suspend fun isAlreadyExist(id: String): Boolean{
        //todo: check is the current job is already exist then delete it.
        return false
    }

    private fun deleteInsertedJob(id: String){

    }
}