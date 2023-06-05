package com.cupcake.jobsfinder.data.repository

import com.cupcake.jobsfinder.data.remote.JobApiService
import com.cupcake.jobsfinder.data.remote.response.JobTitleDto
import com.cupcake.jobsfinder.data.remote.response.PostDto
import com.cupcake.jobsfinder.data.remote.response.base.BaseResponse
import com.cupcake.jobsfinder.data.remote.response.job.JobDto
import com.cupcake.jobsfinder.data.remote.response.job.JobWithTitleDto
import com.cupcake.jobsfinder.domain.repository.Repository
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

    override suspend fun createPost(content: String): Boolean {
        delay(2000)
        return true
    }

    override suspend fun getJobById(jobId: Int): JobDto {
        return wrapResponse { api.getJobById(jobId) }
    }

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

	override suspend fun createJob(jobInfo: JobDto): Boolean {
		return try {
			val job = api.createJob(jobInfo)
			// need more logic
			return true
		} catch (e: Throwable) {
			return false
		}
	}


    // region Job


	//endregion


	// region Post

	override suspend fun getAllPosts(): List<PostDto> {
		return wrapResponseWithErrorHandler { api.getPosts() }
	}

//    override suspend fun getPostById(id: String): PostDto {
//
//        return api.getPostById(id).let { response ->
//            response.body().takeIf {
//                it?.isSuccess ?: false
//            }?.let { post ->
//                post.value ?: throw Exception(response.code().toString())
//            } ?: throw Exception(response.code().toString())
//
//        }
//
//    }

    override suspend fun getPostById(id: String): PostDto {
        return wrapResponseWithErrorHandler {
            api.getPostById(id)
        }
    }


    //endregion


    private suspend fun <T : Any> wrapResponseWithErrorHandler(function: suspend () -> Response<BaseResponse<T>>): T {
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