package com.cupcake.repository.mapper

import com.cupcake.jobsfinder.local.entities.PostsEntity
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

fun Post.toPostsEntity(): PostsEntity{
    return PostsEntity(
        id = id,
        createdAt = createdAt,
        content = content,
        creatorName = creatorName
    )
}


fun PostsEntity.toPost(): Post{
    return Post(
        id = id,
        createdAt = createdAt,
        content = content,
        creatorName = creatorName
    )
}

