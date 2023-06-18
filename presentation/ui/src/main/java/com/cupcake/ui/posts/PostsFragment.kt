package com.cupcake.ui.posts

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.cupcake.ui.R
import com.cupcake.ui.base.BaseFragment
import com.cupcake.ui.databinding.FragmentPostsBinding
import com.cupcake.viewmodels.posts.PostsEvent
import com.cupcake.viewmodels.posts.PostsViewModel
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.math.abs

class PostsFragment : BaseFragment<FragmentPostsBinding, PostsViewModel>(
    R.layout.fragment_posts,
    PostsViewModel::class.java
) {
    override val LOG_TAG: String = this.javaClass.name

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

  //      setupPostsRecyclerView()
        setPostsViewPagerAdapter()
        handleEvent()
    }


//    private fun setupPostsRecyclerView() {
//        val adapter = PostsAdapter(listOf(), viewModel)
//        binding.recyclerViewPosts.adapter = adapter
//    }


    private fun setPostsViewPagerAdapter(){
        val fragments = mapOf(
            FOLLOWING_FRAGMENT to FollowingFragment(),
            PUBLIC_FRAGMENT to PublicFragment()
        )
        val adapter = ViewPagerPostsAdapter(
            fragmentManager = requireActivity().supportFragmentManager,
            fragmentItems = fragments,
            lifecycle = lifecycle
        )
        binding.apply {
            viewPagerPosts.adapter = adapter
            setTabLayout(tabLayoutCategory, viewPagerPosts)
        }
    }

    private fun setUpTransformerViewPager(viewPager: ViewPager2) {
        val transformer = CompositePageTransformer()
        transformer.addTransformer(MarginPageTransformer(20))
        transformer.addTransformer { view, position ->
            if (position <= -1.0F || position >= 1.0F) {
                view.alpha = 0.2F
                view.visibility = View.GONE
            } else if (position == 0.0F) {
                view.alpha = 1.0F
                view.visibility = View.VISIBLE
            } else {
                view.alpha = 1.0F - abs(position)
                view.visibility = View.VISIBLE
            }
        }
        viewPager.setPageTransformer(transformer)
    }



    private fun setTabLayout(tabLayoutCategory : TabLayout, viewPagerCategory : ViewPager2){
        val tabsName = listOf("Public", "Following")
        TabLayoutMediator(tabLayoutCategory, viewPagerCategory){ tab , position ->
            tab.text = tabsName[position]
        }.attach()
        setUpTransformerViewPager(viewPagerCategory)
    }



    private fun handleEvent(){
        lifecycleScope.launch(Dispatchers.Main){
            viewModel.postEvent.collect{postEvent ->
                when(postEvent){
                    is PostsEvent.PostCommentClick ->
                        Toast.makeText(requireContext(), postEvent.id, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }


    companion object{
        const val FOLLOWING_FRAGMENT = 1
        const val PUBLIC_FRAGMENT = 0
    }

}