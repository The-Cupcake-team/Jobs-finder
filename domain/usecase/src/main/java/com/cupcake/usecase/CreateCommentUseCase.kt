package com.cupcake.usecase

import com.cupcake.models.Comment
import repo.JobFinderRepository
import javax.inject.Inject

class CreateCommentUseCase @Inject constructor(
    val repository: JobFinderRepository,
    val validateFieldUseCase: ValidateFieldUseCase
) {
    suspend operator fun invoke(postId: String, content: String): Boolean {
        validateFieldUseCase(content)
        return repository.createComment(postId, content)
    }
}