package com.cupcake.usecase

import com.cupcake.models.Comment
import repo.JobFinderRepository
import javax.inject.Inject

class GetAllCommentsUseCase @Inject constructor(
    private val repository: JobFinderRepository
) {
    suspend operator fun invoke(id:String): List<Comment> {
        return repository.getComments(id)
    }
}