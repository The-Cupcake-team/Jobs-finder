package com.cupcake.jobsfinder.domain.mapper

import com.cupcake.jobsfinder.data.remote.response.PostDto
import com.cupcake.jobsfinder.domain.model.Post

fun PostDto.toPost(): Post{
    return Post(
        id = id ?: "",
        createdAt = createdAt ?: 0,
        content =  content ?: ""
    )
}