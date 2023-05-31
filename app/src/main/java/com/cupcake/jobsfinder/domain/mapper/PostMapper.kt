package com.cupcake.jobsfinder.domain.mapper

import com.cupcake.jobsfinder.data.remote.modle.PostDto
import com.cupcake.jobsfinder.domain.model.Post
import com.cupcake.jobsfinder.mapper.Mapper
import javax.inject.Inject

class PostMapper @Inject constructor() : Mapper<PostDto, Post> {
    override fun mapTo(input: PostDto): Post {
        return Post(
            id = input.id,
            content = input.description,
            createdAt = input.createdAt
        )
    }
}