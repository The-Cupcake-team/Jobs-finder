package com.cupcake.jobsfinder.data.repository

import kotlinx.coroutines.delay
import javax.inject.Inject

class RepositoryImpl: Repository {
	override suspend fun createPost(content: String): Boolean {
		delay(2000)
		return true
	}
}