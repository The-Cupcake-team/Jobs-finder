package com.cupcake.jobsfinder.domain.usecase

import com.cupcake.jobsfinder.data.repository.JobFinderRepository
import com.cupcake.jobsfinder.domain.mapper.toPost
import com.cupcake.jobsfinder.domain.model.Post
import javax.inject.Inject

class GetPostByIdUseCase @Inject constructor(
    private val repository: JobFinderRepository
) {
    suspend operator fun invoke(id:String): Post {
        return repository.getPostById(id).toPost()
    }

}