package com.cupcake.repository

import android.util.Log
import com.cupcake.models.Job
import com.cupcake.models.JobTitle
import com.cupcake.models.JobWithTitle
import com.cupcake.models.Post
import com.cupcake.remote.JobApiService
import com.cupcake.remote.response.base.BaseResponse
import com.cupcake.remote.response.job.JobDto
import com.cupcake.repository.mapper.toJob
import com.cupcake.repository.mapper.toJobWithJobTitle
import com.cupcake.repository.mapper.toPost
import repo.JobFinderRepository
import retrofit2.Response
import javax.inject.Inject


class JobFinderRepositoryImpl @Inject constructor(
    private val api: JobApiService
) : JobFinderRepository {


    // region Job

//	override suspend fun createJob(jobInfo: JobDto): Boolean {
//		val response = api.createJob(
//			jobInfo.jobTitleId,
//			jobInfo.company,
//			jobInfo.workType,
//			jobInfo.jobLocation,
//			jobInfo.jobType,
//			jobInfo.jobDescription,
//			jobInfo.jobSalary
//			)
//		return response.isSuccessful
//	}
//
//
//	override suspend fun getJobById(jobId: String): JobDto {
//		return wrapResponseWithErrorHandler { api.getJobById(jobId) }
//	}
//
//	//endregion
//
//
//	// region Post
//
//
//
//
//	// region Job
//
//
//	//endregion
//
//
//	// region Post
//
//
//	override suspend fun getAllJobTitles(): List<JobTitleDto> {
//		return wrapResponseWithErrorHandler { api.getAllJobTitle() }
//	}
//
//
//	// region Job
//
//
//	//endregion
//
//
//
//	override suspend fun getAllPosts(): List<PostDto> {
//		return wrapResponseWithErrorHandler { api.getPosts() }
//	}
//
//
//	override suspend fun getPostById(id: String): PostDto {
//		return wrapResponseWithErrorHandler {
//			api.getPostById(id)
//		}
//	}


    //endregion


    override suspend fun createJob(jobInfo: Job): Boolean {
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

    override suspend fun getJobs(): List<JobWithTitle> {
        return wrapResponseWithErrorHandler { api.getJobs() }.map { it.toJobWithJobTitle() }
    }

    override suspend fun getAllJobTitles(): List<JobTitle> {
        TODO("Not yet implemented")
    }

    override suspend fun getJobById(jobId: String): Job {
        return wrapResponseWithErrorHandler { api.getJobById(jobId) }.toJob()
    }


    //region Post


    override suspend fun getAllPosts(): List<Post> {
        return wrapResponseWithErrorHandler { api.getPosts() }.map { it.toPost() }
    }

    override suspend fun createPost(content: String): Post {
        return wrapResponseWithErrorHandler { api.createPost(content) }.toPost()
    }

    override suspend fun getPostById(id: String): Post {
        TODO("Not yet implemented")
    }
    //endregion
    private suspend fun <T> wrapResponseWithErrorHandler(
        function: suspend () -> Response<BaseResponse<T>>
    ): T {
        val response = function()
        if (response.isSuccessful) {
            val baseResponse = response.body()
            if (baseResponse != null && baseResponse.isSuccess) {
                Log.i("TAG", "base successful : ${baseResponse.value}")
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