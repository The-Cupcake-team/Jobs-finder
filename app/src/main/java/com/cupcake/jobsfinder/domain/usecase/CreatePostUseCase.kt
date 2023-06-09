package com.cupcake.jobsfinder.domain.useCase

import com.cupcake.jobsfinder.domain.mapper.toPost
import com.cupcake.jobsfinder.domain.model.Post
import com.cupcake.jobsfinder.domain.reposirory.Repository
import javax.inject.Inject


class CreatePostUseCase @Inject constructor(
	private val repository: Repository,
	private val validateFieldUseCade: ValidateFieldUseCade
) {
	suspend operator fun invoke(content: String): Post {
		validateFieldUseCade(content)
		return repository.createPost(content).toPost()
	}
}