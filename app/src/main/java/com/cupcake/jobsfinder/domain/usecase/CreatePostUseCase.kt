package com.cupcake.jobsfinder.domain.usecase

import com.cupcake.jobsfinder.domain.mapper.toPost
import com.cupcake.jobsfinder.domain.model.Post
import com.cupcake.jobsfinder.data.repository.JobFinderRepository
import javax.inject.Inject


class CreatePostUseCase @Inject constructor(
	private val repository: JobFinderRepository,
	private val validateFieldUseCade: ValidateFieldUseCade
) {
	suspend operator fun invoke(content: String): Post {
		validateFieldUseCade(content)
		return repository.createPost(content).toPost()
	}
}