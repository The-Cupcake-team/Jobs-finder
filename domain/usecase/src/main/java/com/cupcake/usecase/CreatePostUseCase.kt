//package com.cupcake.usecase
//
//import com.cupcake.models.Post
//
//
//class CreatePostUseCase @Inject constructor(
//    private val repository: JobFinderRepository,
//    private val validateFieldUseCade: ValidateFieldUseCade
//) {
//	suspend operator fun invoke(content: String): Post {
//		validateFieldUseCade(content)
//		return repository.createPost(content).toPost()
//	}
//}