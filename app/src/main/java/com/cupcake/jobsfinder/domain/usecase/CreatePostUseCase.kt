package com.cupcake.jobsfinder.domain.useCase

import com.cupcake.jobsfinder.data.repository.Repository
import com.cupcake.jobsfinder.domain.mapper.toPost
import com.cupcake.jobsfinder.domain.model.Post
import javax.inject.Inject


class CreatePostUseCase @Inject constructor(
	private val repository: Repository
) {
	suspend operator fun invoke(content: String): Post {
		return repository.createPost(content).toPost()
	}
}