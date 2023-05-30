package com.cupcake.jobsfinder.data.repository

import com.cupcake.jobsfinder.data.remote.modle.PostDto

class RepositoryImpl : Repository {
    override suspend fun getAllPosts(): List<PostDto> {
        return fakePosts()
    }

    private fun fakePosts(): List<PostDto> {
        return listOf(
            PostDto(
                "bilal",
                "android developer",
                "3d ago",
                "bla bla bla",
                "https://blabla.com",
                10,
                9
            ),
            PostDto(
                "devid",
                "android developer",
                "2w ago",
                "bla bla bla",
                "https://devid.com",
                100,
                90
            ),
            PostDto(
                "ameer",
                "android developer",
                "1d ago",
                "",
                "https://ameer.com",
                1200,
                900
            )
        )
    }
}