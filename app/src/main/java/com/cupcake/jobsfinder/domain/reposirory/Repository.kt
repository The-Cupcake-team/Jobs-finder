package com.cupcake.jobsfinder.domain.reposirory

import com.cupcake.jobsfinder.data.remote.response.JobTitleDto
import com.cupcake.jobsfinder.data.remote.response.PostDto
import com.cupcake.jobsfinder.data.remote.response.job.JobDto
import kotlinx.coroutines.flow.Flow

interface Repository {


    // region Job

    suspend fun createJob(jobInfo: JobDto): Boolean

    suspend fun getAllJobs(): Flow<List<JobDto>>

    suspend fun getAllJobTitles(): List<JobTitleDto>

    suspend fun getJobById(jobId: Int): JobDto

    //endregion


    // region Post

    suspend fun createPost(content: String): Boolean

    suspend fun getAllPosts(): Flow<List<PostDto>>

    suspend fun getPostById(id: String): PostDto


    //endregion

}