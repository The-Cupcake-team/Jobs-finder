package com.cupcake.jobsfinder.data.repository

import com.cupcake.jobsfinder.data.remote.JobApiService
import com.cupcake.jobsfinder.data.remote.response.JobTitleDto
import com.cupcake.jobsfinder.data.remote.response.PostDto
import com.cupcake.jobsfinder.data.remote.response.base.BaseResponse
import com.cupcake.jobsfinder.data.remote.response.job.JobDto
import com.cupcake.jobsfinder.domain.reposirory.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
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

	override suspend fun getAllJobs(): Flow<List<JobDto>> {
		return flow {
			val response = api.getAllJobs()
			if (response.isSuccessful) {
				response.body()?.value?.let { emit(it) }
			} else {
				throw Exception(response.message())
			}
		}.flowOn(Dispatchers.IO)

	}

	override suspend fun getAllJobTitles(): List<JobTitleDto> {
		return listOf(
			JobTitleDto(
				id = "ID HERE",
				title = "Android"
			)
		)
	}

	override suspend fun getJobById(jobId: Int): JobDto {
		return wrapResponseWithErrorHandler { api.getJobById(jobId) }
	}

	//endregion

	private fun fakePosts(): Flow<List<PostDto>> {
		return flow {
			emit(
				listOf(
					PostDto("1", 9992453L, "android developer"),
					PostDto("1", 9992453L, "android developer"),
					PostDto("1", 9992453L, "android developer")
				)
			)
		}.flowOn(Dispatchers.IO)
	}


	// region Post

	override suspend fun createPost(content: String): Boolean {
		delay(2000)
		return true
	}

	override suspend fun getAllPosts(): Flow<List<PostDto>> {
		return fakePosts() //todo:[ call getAllPosts from JopApiService]
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
            val errorResponse =  response.errorBody()?.toString()
            throw Throwable(errorResponse ?: "Error Network")
        }

    }
}