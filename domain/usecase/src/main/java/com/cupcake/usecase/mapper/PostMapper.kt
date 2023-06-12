//package com.cupcake.usecase.mapper
//
//import com.cupcake.remote.response.PostDto
//import com.cupcake.models.Post
//
//fun com.cupcake.remote.response.PostDto.toPost(): com.cupcake.models.Post {
//    return Post(
//        id = id ?: "",
//        createdAt = createdAt ?: 0,
//        content =  content ?: ""
//    )
//}