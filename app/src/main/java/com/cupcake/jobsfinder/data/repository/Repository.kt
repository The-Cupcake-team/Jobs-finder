package com.cupcake.jobsfinder.data.repository

import com.cupcake.jobsfinder.data.remote.modle.PostDto
import kotlinx.coroutines.flow.Flow

interface Repository {

    suspend fun getAllPosts(): Flow<List<PostDto>>
}