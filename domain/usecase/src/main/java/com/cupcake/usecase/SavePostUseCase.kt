package com.cupcake.usecase

import com.cupcake.models.Post
import repo.JobFinderRepository
import javax.inject.Inject

class SavePostUseCase @Inject constructor(
    private val repository: JobFinderRepository,
) {


    suspend operator fun invoke(post: Post): Boolean {
        if(isAlreadyExist(post.id)){
            deleteInsertedJob(post)
            return false
        }
        repository.insertPost(post)
        return true
    }

    suspend fun isAlreadyExist(id: String): Boolean{
        repository.getSavedPostById(id) ?: return false
        return true
    }

    private suspend fun deleteInsertedJob(post: Post){
        repository.deletePost(post)
    }
}