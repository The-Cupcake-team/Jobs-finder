package com.cupcake.jobsfinder.data.repository

import com.cupcake.jobsfinder.data.remote.modle.PostDto

class RepositoryImpl : Repository {
    override suspend fun getAllPosts(): List<PostDto> {
        return fakePosts()
    }

    private fun fakePosts(): List<PostDto> {
        return listOf(
            PostDto(
                1,
                "bilal",
                "android developer",
                212453L,
                "bla bla 1",
                "https://blabla.com",
                10,
                9
            ),
            PostDto(
                2,
                "david",
                "android developer",
                662453L,
                "bla bla 2",
                "https://david.com",
                1000,
                90
            ),
            PostDto(
                3,
                "ameer",
                "android developer",
                855453L,
                "bla bla 3",
                "https://david.com",
                10000,
                900
            ),


        )
    }
}