package com.cupcake.jobsfinder.data.remote

import com.cupcake.jobsfinder.data.remote.modle.PostDto
import retrofit2.http.GET

interface JobApiService {

    @GET("/posts")
    suspend fun getAllPosts(): List<PostDto>
}