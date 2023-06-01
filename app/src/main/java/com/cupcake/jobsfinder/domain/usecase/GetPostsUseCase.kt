package com.cupcake.jobsfinder.domain.usecase

import com.cupcake.jobsfinder.data.repository.Repository
import com.cupcake.jobsfinder.domain.mapper.toPost
import com.cupcake.jobsfinder.domain.model.Post
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetPostsUseCase @Inject constructor(
    private val repository: Repository
) {
    suspend operator fun invoke(): Flow<List<Post>> {
        return repository.getAllPosts().map { postDto ->
            postDto.map { it.toPost() }
        }
    }
}