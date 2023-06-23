package com.cupcake.repository

import android.util.Log
import com.cupcake.jobsfinder.local.daos.JobFinderDao
import com.cupcake.jobsfinder.local.entities.JobsEntity
import com.cupcake.models.ErrorType
import com.cupcake.models.Job
import com.cupcake.models.JobTitle
import com.cupcake.models.Post
import com.cupcake.remote.JobApiService
import com.cupcake.remote.response.base.BaseResponse
import com.cupcake.repository.mapper.toJob
import com.cupcake.repository.mapper.toJobTitle
import com.cupcake.repository.mapper.toJobsEntity
import com.cupcake.repository.mapper.toPost
import com.cupcake.repository.mapper.toPostsEntity
import repo.JobFinderRepository
import retrofit2.Response
import javax.inject.Inject


class JobFinderRepositoryImpl @Inject constructor(
    private val api: JobApiService,
    private val jobFinderDao: JobFinderDao
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
            jobInfo.jobTitle.id?.toInt(),
            jobInfo.company,
            jobInfo.workType,
            jobInfo.jobLocation,
            jobInfo.jobType,
            jobInfo.jobDescription,
            jobInfo.jobSalary.maxSalary,
            jobInfo.jobSalary.minSalary,
            jobInfo.jobExperience,
            jobInfo.education
        )
        return response.isSuccessful
    }

    override suspend fun getJobs(): List<Job> {
        return wrapResponseWithErrorHandler { api.getJobs() }.map { it.toJob() }
    }

    override suspend fun getAllJobTitles(): List<JobTitle> {
        return wrapResponseWithErrorHandler { api.getAllJobTitle() }.map { it.toJobTitle() }
    }

    override suspend fun getJobById(jobId: String): Job {
        return wrapResponseWithErrorHandler { api.getJobById(jobId) }.toJob()
    }

    override suspend fun insertJob(job: Job) {
        val jobEntity = job.toJobsEntity()
        jobFinderDao.insertJob(jobEntity)
    }

    override suspend fun deleteJob(job: Job) {
        val jobEntity = job.toJobsEntity()
        jobFinderDao.deleteSavedJob(jobEntity)
    }

    override suspend fun getSavedJobById(id: String): Job? {
        val jobEntity: JobsEntity? = jobFinderDao.getJopById(id)
        return jobEntity?.toJob()
    }


    //region Post


    override suspend fun getAllPosts(): List<Post> {
        return wrapResponseWithErrorHandler { api.getPosts() }.map { it.toPost() }
    }


    override suspend fun getFollowingPosts(): List<Post> {
        val fakePosts = listOf(
            Post("1", "2023-06-23T13:56:42.584743", "One Piece 🏴‍☠️❤️‍🔥", "Sajjadio"),
            Post("2", "2023-06-23T13:56:42.584743", "Sabahooooooo 👋", "amory" ),
            Post("4", "2023-06-23T13:56:42.584743", "here we are go 🤍❤️", "dada"),
            Post("5", "2023-06-23T13:56:42.584743", "MY TEAM IS THE BEST 🧁🔝💖💖💖", "ahmed mousa"),
            Post("6", "2023-06-23T13:56:42.584743", "MY TEAM MATES ARE AWESOME 😍🤩💖", "kaido"),
            Post("7", "2023-06-23T13:56:42.584743", "FK you haters 🫵😎✊", "BK")
        )
        return fakePosts
    }

    override suspend fun insertPost(post: Post) {
        jobFinderDao.insertPost(post.toPostsEntity())
    }

    override suspend fun deletePost(post: Post) {
        jobFinderDao.deleteSavedPost(post.toPostsEntity())
    }

    override suspend fun getSavedPostById(id: String): Post? {
       return jobFinderDao.getPostById(id)?.toPost()
    }

    override suspend fun createPost(content: String): Post {
        return wrapResponseWithErrorHandler { api.createPost(content) }.toPost()
    }

    override suspend fun getPostById(id: String): Post {
        return wrapResponseWithErrorHandler { api.getPostById(id) }.toPost()
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
//                throw Throwable("Invalid response")
                throw ErrorType.Server(baseResponse?.message!!)

            }
        } else {
            val errorResponse = response.errorBody()?.toString()
//            throw Throwable(errorResponse ?: "Error Network")
            throw ErrorType.Server(errorResponse ?: "Error Network")
        }

    }
}