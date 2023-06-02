package com.cupcake.jobsfinder.data.repository

import com.cupcake.jobsfinder.data.remote.response.JobDto

interface Repository {


  suspend fun createJob(jobInfo: JobDto): Boolean

	suspend fun createPost(content: String): Boolean

}