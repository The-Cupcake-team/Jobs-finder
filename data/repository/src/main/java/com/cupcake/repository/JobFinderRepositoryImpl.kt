package com.cupcake.repository

import android.util.Log
import com.cupcake.jobsfinder.local.daos.JobFinderDao
import com.cupcake.jobsfinder.local.datastore.ProfileDataStore
import com.cupcake.jobsfinder.local.entities.JobsEntity
import com.cupcake.models.Comment
import com.cupcake.models.ErrorType
import com.cupcake.models.Job
import com.cupcake.models.JobTitle
import com.cupcake.models.Post
import com.cupcake.remote.JobApiService
import com.cupcake.remote.response.base.BaseResponse
import com.cupcake.repository.mapper.toComment
import com.cupcake.repository.mapper.toJob
import com.cupcake.repository.mapper.toJobTitle
import com.cupcake.repository.mapper.toPost
import com.cupcake.repository.mapper.toPostsEntity
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import repo.JobFinderRepository
import retrofit2.Response
import java.io.File
import javax.inject.Inject


class JobFinderRepositoryImpl @Inject constructor(
    private val api: JobApiService,
    private val jobFinderDao: JobFinderDao,
    private val profileDataStore: ProfileDataStore
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
            Post("1", "2023-06-23T13:56:42.584743", "One Piece 🏴‍☠️❤️‍🔥", "Sajjadio" , "https://images.unsplash.com/photo-1661956602153-23384936a1d3?ixlib=rb-4.0.3&ixid=M3wxMjA3fDF8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=1770&q=80", "Developer" , "https://static.vecteezy.com/system/resources/previews/000/439/863/original/vector-users-icon.jpg" ),
            Post("2", "2023-06-23T13:56:42.584743", "Sabahooooooo 👋", "amory" , "https://images.unsplash.com/photo-1661956602153-23384936a1d3?ixlib=rb-4.0.3&ixid=M3wxMjA3fDF8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=1770&q=80", "Developer" , "https://static.vecteezy.com/system/resources/previews/000/439/863/original/vector-users-icon.jpg"),
            Post("4", "2023-06-23T13:56:42.584743", "here we are go 🤍❤️", "dada" , "https://images.unsplash.com/photo-1661956602153-23384936a1d3?ixlib=rb-4.0.3&ixid=M3wxMjA3fDF8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=1770&q=80","Developer" , "https://static.vecteezy.com/system/resources/previews/000/439/863/original/vector-users-icon.jpg"),
            Post("5", "2023-06-23T13:56:42.584743", "MY TEAM IS THE BEST 🧁🔝💖💖💖", "ahmed mousa" ,"https://images.unsplash.com/photo-1661956602153-23384936a1d3?ixlib=rb-4.0.3&ixid=M3wxMjA3fDF8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=1770&q=80", "home less" , "https://static.vecteezy.com/system/resources/previews/000/439/863/original/vector-users-icon.jpg"),
            Post("6", "2023-06-23T13:56:42.584743", "MY TEAM MATES ARE AWESOME 😍🤩💖", "kaido" , "https://images.unsplash.com/photo-1661956602153-23384936a1d3?ixlib=rb-4.0.3&ixid=M3wxMjA3fDF8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=1770&q=80", "Developer" , "https://static.vecteezy.com/system/resources/previews/000/439/863/original/vector-users-icon.jpg"),
            Post("7", "2023-06-23T13:56:42.584743", "The Chance 😎", "BK" , "https://images.unsplash.com/photo-1661956602153-23384936a1d3?ixlib=rb-4.0.3&ixid=M3wxMjA3fDF8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=1770&q=80", "Developer" , "https://static.vecteezy.com/system/resources/previews/000/439/863/original/vector-users-icon.jpg")
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

    override suspend fun createPost(content: String, image: File?): Post {
        val contentRequestBody = content.toRequestBody("text/plain".toMediaTypeOrNull())

        val imagePart: MultipartBody.Part? = image?.let {
            val imageRequestBody = it.asRequestBody("image/*".toMediaTypeOrNull())
            MultipartBody.Part.createFormData("image", it.name, imageRequestBody)
        }

        return wrapResponseWithErrorHandler { api.createPost(contentRequestBody, imagePart) }.toPost()
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

    // region DataStore
    override suspend fun saveProfileData(avatarUri: String, jobTitle: Int) {
        profileDataStore.saveProfileData(avatarUri, jobTitle)
    }

    override suspend fun getAvatarUri(): String? {
        return profileDataStore.getAvatarUri()
    }

    override suspend fun getJobTitle(): Int? {
        return profileDataStore.getJobTitle()
    }

    override suspend fun clearProfileData() {
        profileDataStore.clearProfileData()
    }

    //endregion

    //region Comments
    override suspend fun createComment(postId: String, content: String): Boolean {
        val response = api.createComment(postId, content)
        if (response.isSuccessful) {
            val baseResponse = response.body()
            if (baseResponse != null && baseResponse.isSuccess) {
                return true
            } else {
                throw ErrorType.Server(baseResponse?.message!!)
            }
        }
        return false
    }
    override suspend fun getComments(id: String): List<Comment> {
        return wrapResponseWithErrorHandler { api.getComments(id) }.map { it.toComment() }
    }
    //endregion

}