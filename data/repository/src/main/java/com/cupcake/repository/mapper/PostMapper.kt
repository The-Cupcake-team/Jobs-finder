package com.cupcake.repository.mapper

import com.cupcake.jobsfinder.local.entities.PostsEntity
import com.cupcake.models.Post
import com.cupcake.remote.response.PostsDto

fun PostsDto.toPost(): Post {
    return Post(
        id = id ?: "",
        createdAt = createdAt ?: "",
        content = content ?: "",
        creatorName = author?.name ?:"",
        postImage = image?.imageUrl ?: "",
        jobTitle = author?.jobTitle ?: "",
        profileImage = author?.avtar ?: ""
    )
}

fun Post.toPostsEntity(): PostsEntity{
    return PostsEntity(
        id = id,
        createdAt = createdAt,
        content = content,
        creatorName = creatorName,
        postImage = postImage,
        jobTitle =jobTitle,
        profileImage = profileImage
    )
}


fun PostsEntity.toPost(): Post{
    return Post(
        id = id,
        createdAt = createdAt,
        content = content,
        creatorName = creatorName,
        postImage = postImage,
        jobTitle =jobTitle,
        profileImage = profileImage
    )
}

