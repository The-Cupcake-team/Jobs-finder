package com.cupcake.repository

import com.cupcake.remote.response.JobTitleDto
import com.cupcake.remote.response.job.JobDto
import com.cupcake.remote.response.job.JobWithTitleDto

interface JobFinderRepository {


    // region Job

    suspend fun createJob(jobInfo: JobDto): Boolean

    suspend fun getJobs(): List<JobWithTitleDto>

    suspend fun getAllJobTitles(): List<JobTitleDto>

    suspend fun getJobById(jobId: String): JobDto

    //endregion


    // region Post

    suspend fun createPost(content: String): com.cupcake.remote.response.PostDto

    suspend fun getAllPosts(): List<com.cupcake.remote.response.PostDto>

    suspend fun getPostById(id: String): com.cupcake.remote.response.PostDto


    //endregion

}