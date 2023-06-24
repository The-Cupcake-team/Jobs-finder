package com.cupcake.repository.mapper

import com.cupcake.models.Comment
import com.cupcake.remote.response.CommentDto

fun CommentDto.toComment(): Comment{
    return Comment(
        id = id ?: "",
        postId = postId ?: "",
        totalLikes = totalLike ?: 0,
        content = content ?: "",
        createAt = createAt ?: "",
        commentAuthor = author?.name ?: "",
        jobTitle = author?.jobTitle ?: "",
        profileImage = author?.avtar ?: ""
    )
}