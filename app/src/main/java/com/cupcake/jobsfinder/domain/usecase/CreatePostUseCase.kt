package com.cupcake.jobsfinder.domain.usecase

import com.cupcake.jobsfinder.data.repository.Repository
import javax.inject.Inject


class CreatePostUseCase @Inject constructor(
	private val repository: Repository
) {
	suspend operator fun invoke(content: String): Boolean {
		return repository.createPost(content)
	}
}