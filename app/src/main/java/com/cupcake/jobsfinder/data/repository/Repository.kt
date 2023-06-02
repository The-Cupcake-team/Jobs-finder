package com.cupcake.jobsfinder.data.repository

import com.cupcake.jobsfinder.data.remote.dtos.JobDto

interface Repository {
	suspend fun createPost(content: String): Boolean
	suspend fun getJobById(jobId: Int) : JobDto
}