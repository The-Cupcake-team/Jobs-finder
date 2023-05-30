package com.cupcake.jobsfinder.data.repository

import com.cupcake.jobsfinder.data.remote.modle.PostDto

interface Repository {

    suspend fun getAllPosts(): List<PostDto>
}