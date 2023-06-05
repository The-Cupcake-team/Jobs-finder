package com.cupcake.jobsfinder.domain.usecase

import com.cupcake.jobsfinder.domain.mapper.toPost
import com.cupcake.jobsfinder.domain.model.Post
import com.cupcake.jobsfinder.domain.reposirory.Repository
import javax.inject.Inject

class GetPostsUseCase @Inject constructor(
    private val repository: Repository
) {
    suspend operator fun invoke(): List<Post> {
        return repository.getAllPosts().map { it.toPost() }
    }
}