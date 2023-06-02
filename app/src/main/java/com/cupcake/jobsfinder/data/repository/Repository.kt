package com.cupcake.jobsfinder.data.repository

import com.cupcake.jobsfinder.data.remote.response.JobDto
import com.cupcake.jobsfinder.data.dto.JobTitleDto
import com.cupcake.jobsfinder.data.remote.modle.PostDto
import kotlinx.coroutines.flow.Flow

interface Repository {
  
  suspend fun getAllPosts(): Flow<List<PostDto>>

  suspend fun getAllJobTitles():List<JobTitleDto>
  
  suspend fun createJob(jobInfo: JobDto): Boolean

	suspend fun createPost(content: String): Boolean

}