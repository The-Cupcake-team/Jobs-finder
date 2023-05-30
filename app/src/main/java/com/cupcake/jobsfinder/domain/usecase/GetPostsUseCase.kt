package com.cupcake.jobsfinder.domain.usecase

import com.cupcake.jobsfinder.data.repository.Repository
import com.cupcake.jobsfinder.domain.mapper.PostMapper
import com.cupcake.jobsfinder.domain.model.Post
import javax.inject.Inject

class GetPostsUseCase @Inject constructor(
    private val repository: Repository,
    private val postMapper: PostMapper
) {
    operator suspend fun invoke(): List<Post> {
        return repository.getAllPosts().map { postDto ->
            postMapper.mapTo(postDto)
        }.sortedBy { it.username }
    }
}