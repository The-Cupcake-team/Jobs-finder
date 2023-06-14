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
//	override suspend fun getJobs(): List<JobWithTitleDto> {
//		return wrapResponseWithErrorHandler { api.getJobs() }
//	}
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
//	// region Post
//	override suspend fun createPost(content: String): PostDto {
//		return wrapResponseWithErrorHandler {
//			api.createPost(content)
//		}
//	}
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
        TODO("Not yet implemented")
    }

    override suspend fun getJobs(): List<JobWithTitle> {
        TODO("Not yet implemented")
    }

    override suspend fun getAllJobTitles(): List<JobTitle> {
        TODO("Not yet implemented")
    }

    override suspend fun getJobById(jobId: String): Job {
//     DON'T WORK
//        return wrapResponseWithErrorHandler { api.getJobById(jobId) }.toJob()

        return Job(
            id = "438cd8f7-af62-4796-84be-a98807e874d8",
            jobTitleId = 1234,
            company = "Google",
            createdAt = 1234,
            workType = "application",
            jobLocation = "Iraq",
            jobType = "application",
            jobDescription = "Job description",
            jobSalary = 12.34
        )
    }




    override suspend fun createPost(content: String): Post {
        TODO("Not yet implemented")
    }

    override suspend fun getAllPosts(): List<Post> {
        return wrapResponseWithErrorHandler { api.getPosts() }.map { it.toPost() }
    }

    override suspend fun getPostById(id: String): Post {
        TODO("Not yet implemented")
    }

    private suspend fun <T> wrapResponseWithErrorHandler(
        function: suspend () -> Response<BaseResponse<T>>
    ): T {
        Log.i("TAG", "Start")
        val response = function()
        Log.i("TAG", "response ${response}")

        if (response.isSuccessful) {
            Log.i("TAG", "Success")
            val baseResponse = response.body()
            Log.i("TAG", "base : ${baseResponse}")

            if (baseResponse != null && baseResponse.isSuccess) {
                Log.i("TAG", "base successful : ${baseResponse.value}")
                return baseResponse.value!!
            } else {
                Log.i("TAG", "base fail ")
                throw Throwable("Invalid response")
            }
        } else {
            val errorResponse = response.errorBody()?.toString()
            throw Throwable(errorResponse ?: "Error Network")
        }

    }
}