package com.cupcake.repository

import android.util.Log
import com.cupcake.jobsfinder.local.daos.JobFinderDao
import com.cupcake.jobsfinder.local.datastore.ProfileDataStore
import com.cupcake.jobsfinder.local.entities.JobsEntity
import com.cupcake.models.ErrorType
import com.cupcake.models.Job
import com.cupcake.models.JobTitle
import com.cupcake.models.Post
import com.cupcake.remote.JobApiService
import com.cupcake.remote.response.base.BaseResponse
import com.cupcake.repository.mapper.toJob
import com.cupcake.repository.mapper.toJobTitle
import com.cupcake.repository.mapper.toJobTitleEntity
import com.cupcake.repository.mapper.toJobsEntity
import com.cupcake.repository.mapper.toPost
import repo.JobFinderRepository
import retrofit2.Response
import javax.inject.Inject


class JobFinderRepositoryImpl @Inject constructor(
    private val api: JobApiService,
    private val jobFinderDao: JobFinderDao,
    private val profileDataStore: ProfileDataStore
) : JobFinderRepository {

    // region Job
    override suspend fun createJob(jobInfo: Job): Boolean {
        val response = api.createJob(
            jobInfo.jobTitle.id,
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
        return jobFinderDao.getJobTitles().map { it.toJobTitle() }
    }

    override suspend fun refreshJobTitles() {
        wrapResponseWithErrorHandler { api.getAllJobTitle() }
            .map { jobFinderDao.insertJobTitles(it.toJobTitleEntity()) }
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

    //endregion


    //region Post

    override suspend fun getAllPosts(): List<Post> {
        return wrapResponseWithErrorHandler { api.getPosts() }.map { it.toPost() }
    }


    override suspend fun getFollowingPosts(): List<Post> {
        val fakePosts = listOf(
            Post("1", System.currentTimeMillis().toString(), "One Piece 🏴‍☠️❤️‍🔥", "Sajjadio"),
            Post("2", System.currentTimeMillis().toString(), "Sabahooooooo 👋", "amory"),
            Post("4", System.currentTimeMillis().toString(), "here we are go 🤍❤️", "dada"),
            Post(
                "5",
                System.currentTimeMillis().toString(),
                "MY TEAM IS THE BEST 🧁🔝💖💖💖",
                "ahmed mousa"
            ),
            Post(
                "6",
                System.currentTimeMillis().toString(),
                "MY TEAM MATES ARE AWESOME 😍🤩💖",
                "kaido"
            ),
            Post("7", System.currentTimeMillis().toString(), "FK you haters 🫵😎✊", "BK")
        )
        return fakePosts
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

    // region DataStore
    override suspend fun saveProfileData(avatarUri: String, jobTitle: Int) {
        profileDataStore.saveProfileData(avatarUri, jobTitle)
    }

    override suspend fun getAvatarUri(): String? {
        return profileDataStore.getAvatarUri()
    }

    override suspend fun getUserJobTitleId(): Int? {
        return profileDataStore.getJobTitle()
    }

    override suspend fun clearProfileData() {
        profileDataStore.clearProfileData()
    }

    //endregion

}