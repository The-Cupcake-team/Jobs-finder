package com.cupcake.jobsfinder.data.repository

import com.cupcake.jobsfinder.data.remote.JobApiService
import com.cupcake.jobsfinder.data.remote.dtos.BaseResponse
import com.cupcake.jobsfinder.data.remote.dtos.JobDto
import kotlinx.coroutines.delay
import retrofit2.Response
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
	private val jobApiService: JobApiService
) : Repository {
	override suspend fun createPost(content: String): Boolean {
		delay(2000)
		return true
	}

	override suspend fun getJobById(jobId: Int): JobDto {
		return wrapResponse{ jobApiService.getJobById(jobId) }
	}

	private suspend fun <T> wrapResponse(
		function: suspend () -> Response<BaseResponse<T>>
	): T {
		val response = function()
		return if (response.isSuccessful) {
			response.body()?.value?.first() ?: throw Throwable()
		} else {
			throw Throwable("response is not successful")
		}
	}
}