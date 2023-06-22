package com.cupcake.viewmodels.posts

import com.cupcake.viewmodels.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PostsViewModel  @Inject constructor() : BaseViewModel<PostsUIState>(PostsUIState())
{

}