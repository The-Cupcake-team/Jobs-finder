package com.cupcake.jobsfinder.domain.usecase

import com.cupcake.jobsfinder.domain.mapper.toPost
import com.cupcake.jobsfinder.domain.model.Post
import com.cupcake.jobsfinder.data.repository.JobFinderRepository
import javax.inject.Inject

class GetPostsUseCase @Inject constructor(
    private val repository: JobFinderRepository
) {
    suspend operator fun invoke(): List<Post> {
        return repository.getAllPosts().map { it.toPost() }
    }
}