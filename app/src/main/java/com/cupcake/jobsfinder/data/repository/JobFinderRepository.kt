package com.cupcake.jobsfinder.data.repository

import com.cupcake.jobsfinder.data.remote.response.JobTitleDto
import com.cupcake.jobsfinder.data.remote.response.PostDto
import com.cupcake.jobsfinder.data.remote.response.job.JobDto
import com.cupcake.jobsfinder.data.remote.response.job.JobWithTitleDto

interface JobFinderRepository {


    // region Job

    suspend fun createJob(jobInfo: JobDto): Boolean

    suspend fun getJobs(): List<JobWithTitleDto>

    suspend fun getAllJobTitles(): List<JobTitleDto>

    suspend fun getJobById(jobId: Int): JobDto

    //endregion


    // region Post

    suspend fun createPost(content: String): PostDto

    suspend fun getAllPosts(): List<PostDto>

    suspend fun getPostById(id: String): PostDto


    //endregion

}