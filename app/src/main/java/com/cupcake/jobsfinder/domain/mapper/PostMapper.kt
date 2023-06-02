package com.cupcake.jobsfinder.domain.mapper

import com.cupcake.jobsfinder.data.remote.modle.PostDto
import com.cupcake.jobsfinder.domain.model.Post

fun PostDto.toPost(): Post{
    return Post(
        id = this.id,
        createdAt = this.createdAt,
        content =  this.description
    )
}