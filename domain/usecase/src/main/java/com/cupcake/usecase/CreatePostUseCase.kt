package com.cupcake.usecase

import com.cupcake.models.Post
import repo.JobFinderRepository
import java.io.File
import javax.inject.Inject


class CreatePostUseCase @Inject constructor(
    private val repository: JobFinderRepository,
    private val validateField: ValidateFieldUseCase,
) {
    suspend operator fun invoke(content: String): Post {
        validateField(content)
        return repository.createPost(content)
    }

}