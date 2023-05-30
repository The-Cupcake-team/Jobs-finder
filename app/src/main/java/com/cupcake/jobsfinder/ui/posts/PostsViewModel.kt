package com.cupcake.jobsfinder.ui.posts

import com.cupcake.jobsfinder.domain.usecase.GetPostsUseCase
import com.cupcake.jobsfinder.ui.base.BaseViewModel
import javax.inject.Inject

class PostsViewModel @Inject constructor(
    private val getPostsUseCase: GetPostsUseCase
): BaseViewModel() {


}