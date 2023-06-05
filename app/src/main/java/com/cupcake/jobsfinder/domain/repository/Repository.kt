package com.cupcake.jobsfinder.domain.repository

import com.cupcake.jobsfinder.data.remote.response.JobTitleDto
import com.cupcake.jobsfinder.data.remote.response.PostDto
import com.cupcake.jobsfinder.data.remote.response.job.JobDto
import com.cupcake.jobsfinder.data.remote.response.job.JobWithTitleDto
import kotlinx.coroutines.flow.Flow

interface Repository {

  // region post
  suspend fun getAllPosts(): List<PostDto>
  //endregion
  suspend fun getAllJobTitles():List<JobTitleDto>

  suspend fun createJob(jobInfo: JobDto): Boolean


    // region Job

  suspend fun getJobs(): List<JobWithTitleDto>

  suspend fun getJobById(jobId: Int): JobDto

    //endregion


    // region Post
    suspend fun createPost(content: String): PostDto



    suspend fun getPostById(id: String): PostDto


    //endregion

}