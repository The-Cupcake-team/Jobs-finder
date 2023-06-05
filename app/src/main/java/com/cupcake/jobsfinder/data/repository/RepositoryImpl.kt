package com.cupcake.jobsfinder.data.repository

import com.cupcake.jobsfinder.data.remote.JobApiService
import com.cupcake.jobsfinder.data.remote.response.JobTitleDto
import com.cupcake.jobsfinder.data.remote.response.PostDto
import com.cupcake.jobsfinder.data.remote.response.base.BaseResponse
import com.cupcake.jobsfinder.data.remote.response.job.JobDto
import com.cupcake.jobsfinder.data.remote.response.job.JobWithTitleDto
import com.cupcake.jobsfinder.domain.reposirory.Repository
import retrofit2.Response
import javax.inject.Inject


class RepositoryImpl @Inject constructor(
	private val api: JobApiService
) : Repository {


	// region Job

	override suspend fun createJob(jobInfo: JobDto): Boolean {
		val response = api.createJob(
			jobInfo.jobTitleId,
			jobInfo.company,
			jobInfo.workType,
			jobInfo.jobLocation,
			jobInfo.jobType,
			jobInfo.jobDescription,
			jobInfo.jobSalary
			)
		return response.isSuccessful
	}


	override suspend fun getJobById(jobId: Int): JobDto {
		return wrapResponseWithErrorHandler { api.getJobById(jobId) }
	}

	//endregion


	// region Post


	private suspend fun <T> wrapResponse(
		function: suspend () -> Response<BaseResponse<T>>
	): T {
		val response = function()
		return if (response.isSuccessful) {
			response.body()?.value ?: throw Throwable()
		} else {
			throw Throwable("response is not successful")
		}
	}


	// region Job

	override suspend fun getJobs(): List<JobWithTitleDto> {
		return wrapResponseWithErrorHandler { api.getJobs() }
	}

	//endregion


	// region Post


	override suspend fun getAllJobTitles(): List<JobTitleDto> {
		return wrapResponseWithErrorHandler { api.getAllJobTitle() }
	}


	// region Job


	//endregion


	// region Post
	override suspend fun createPost(content: String): PostDto {
		return wrapResponseWithErrorHandler {
			api.createPost(content)
		}
	}

	override suspend fun getAllPosts(): List<PostDto> {
		return wrapResponseWithErrorHandler { api.getPosts() }
	}


	override suspend fun getPostById(id: String): PostDto {
		return wrapResponseWithErrorHandler {
			api.getPostById(id)
		}
	}


	//endregion


	private suspend fun <T> wrapResponseWithErrorHandler(
		function: suspend () -> Response<BaseResponse<T>>
	): T {
		val response = function()

		if (response.isSuccessful) {
			val baseResponse = response.body()
			if (baseResponse != null && baseResponse.isSuccess) {
				return baseResponse.value!!
			} else {
				throw Throwable("Invalid response")
			}
		} else {
			val errorResponse = response.errorBody()?.toString()
			throw Throwable(errorResponse ?: "Error Network")
		}

	}
}