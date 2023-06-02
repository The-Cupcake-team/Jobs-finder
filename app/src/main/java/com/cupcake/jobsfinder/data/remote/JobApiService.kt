package com.cupcake.jobsfinder.data.remote

import com.cupcake.jobsfinder.data.remote.modle.PostDto
import retrofit2.http.GET
import com.cupcake.jobsfinder.data.remote.response.JobDto
import retrofit2.http.Body
import retrofit2.http.POST

interface JobApiService {

  
    @GET("/posts")
    suspend fun getAllPosts(): List<PostDto>
  
    @POST("/jobs")
    suspend fun createJob(@Body job: JobDto): JobDto
  

}