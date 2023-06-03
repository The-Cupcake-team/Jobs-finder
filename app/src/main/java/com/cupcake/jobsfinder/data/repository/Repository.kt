package com.cupcake.jobsfinder.data.repository

import com.cupcake.jobsfinder.data.remote.response.job.JobDto
import kotlinx.coroutines.flow.Flow
import com.cupcake.jobsfinder.data.dto.JobTitleDto
import com.cupcake.jobsfinder.data.remote.modle.PostDto

interface Repository {

  suspend fun getAllPosts(): Flow<List<PostDto>>

  suspend fun getAllJobTitles():List<JobTitleDto>

  suspend fun createJob(jobInfo: JobDto): Boolean

  suspend fun getAllJobs(): Flow<List<JobDto>>

	suspend fun createPost(content: String): Boolean


	suspend fun getJobById(jobId: Int) : JobDto
}