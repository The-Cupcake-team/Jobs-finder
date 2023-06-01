package com.cupcake.jobsfinder.data.repository

import com.cupcake.jobsfinder.data.remote.modle.PostDto
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class RepositoryImpl @Inject constructor(): Repository {
    override suspend fun getAllPosts(): Flow<List<PostDto>> {
        return fakePosts() //todo:[ call getAllPosts from JopApiService]
    }

    private fun fakePosts(): Flow<List<PostDto>> {
        return flow {
            emit(
                listOf(
                    PostDto("1", 9992453L, "android developer",),
                    PostDto("1", 9992453L, "android developer",),
                    PostDto("1", 9992453L, "android developer",)
                )
            )
        }.flowOn(Dispatchers.IO)
    }
}