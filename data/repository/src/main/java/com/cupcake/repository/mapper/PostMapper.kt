package com.cupcake.repository.mapper

import com.cupcake.models.Post
import com.cupcake.remote.response.PostDto

fun PostDto.toPost(): Post {
    return Post(
        id = id ?: "",
        createdAt = createdAt ?: 0,
        content = content ?: "",
        creatorName = creatorName ?: ""
    )
}