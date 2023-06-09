package com.cupcake.usecase

import repo.JobFinderRepository
import com.cupcake.models.Post
import javax.inject.Inject

class GetPostByIdUseCase @Inject constructor(
    private val repository: JobFinderRepository
) {
    suspend operator fun invoke(id:String): Post {
        return repository.getPostById(id)
    }

}