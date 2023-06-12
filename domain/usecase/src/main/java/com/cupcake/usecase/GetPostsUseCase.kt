//package com.cupcake.usecase
//
//
//import com.cupcake.models.Post
//import javax.inject.Inject
//
//class GetPostsUseCase @Inject constructor(
//    private val repository: JobFinderRepository
//) {
//    suspend operator fun invoke(): List<Post> {
//        return repository.getAllPosts().map { it.toPost() }
//    }
//}