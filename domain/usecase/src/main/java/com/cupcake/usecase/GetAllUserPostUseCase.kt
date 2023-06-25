package com.cupcake.usecase

import com.cupcake.models.Post
import repo.JobFinderRepository
import javax.inject.Inject

class GetAllUserPostUseCase@Inject constructor(
    private val repository: JobFinderRepository
) {
    suspend operator fun invoke():List<Post>{
        return repository.getAllUserPost()
    }
}