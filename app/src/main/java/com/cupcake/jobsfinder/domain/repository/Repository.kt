package com.cupcake.jobsfinder.domain.repository

import com.cupcake.jobsfinder.data.remote.response.JobTitleDto
import com.cupcake.jobsfinder.data.remote.response.PostDto
import com.cupcake.jobsfinder.data.remote.response.job.JobDto
import com.cupcake.jobsfinder.data.remote.response.job.JobWithJobTitleDto
import kotlinx.coroutines.flow.Flow

interface Repository {

  suspend fun getAllPosts(): Flow<List<PostDto>>

  suspend fun getAllJobTitles():List<JobTitleDto>

  suspend fun createJob(jobInfo: JobDto): Boolean


	suspend fun createPost(content: String): Boolean

    // region Job

  suspend fun getJobs(): List<JobWithJobTitleDto>

  suspend fun getJobById(jobId: Int): JobDto

    //endregion


    // region Post



    suspend fun getPostById(id: String): PostDto


    //endregion

}