package com.cupcake.ui.posts

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.cupcake.ui.R
import com.cupcake.ui.base.BaseFragment
import com.cupcake.ui.databinding.FragmentPostsBinding
import com.cupcake.viewmodels.posts.PostsViewModel

import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import kotlin.math.abs

class PostsFragment : BaseFragment<FragmentPostsBinding, PostsViewModel>(
    R.layout.fragment_posts,
    PostsViewModel::class.java
) {
    override val LOG_TAG: String = this.javaClass.name
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setPostsViewPagerAdapter()
    }

    private fun setPostsViewPagerAdapter() {
        val fragmentList = mutableListOf<Fragment>()
        val fragments = mapOf(
            PUBLIC_FRAGMENT to PublicFragment(),
            FOLLOWING_FRAGMENT to FollowingFragment()
        )
        fragmentList.clear()

        for (fragment in fragments.values) {
            fragmentList.add(fragment)
        }
        val adapter = ViewPagerPostsAdapter(
            fragmentManager = childFragmentManager,
            fragmentList = fragmentList,
            lifecycle = lifecycle
        )
        binding.viewPagerPosts.adapter = adapter
        setTabLayout(binding.tabLayoutCategory, binding.viewPagerPosts)
    }

    private fun setTabLayout(tabLayoutCategory: TabLayout, viewPagerCategory: ViewPager2) {
        val tabsName = listOf("Public", "Following")
        TabLayoutMediator(tabLayoutCategory, viewPagerCategory) { tab, position ->
            tab.text = tabsName[position]
        }.attach()

        viewPagerCategory.post {
            setUpTransformerViewPager(viewPagerCategory)
        }
    }


    private fun setUpTransformerViewPager(viewPager: ViewPager2) {
        val transformer = CompositePageTransformer()
        transformer.addTransformer(MarginPageTransformer(20))
        transformer.addTransformer { view, position ->
            if (position <= -1.0F || position >= 1.0F) {
                view.alpha = 0.0F
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

    companion object{
        const val FOLLOWING_FRAGMENT = 1
        const val PUBLIC_FRAGMENT = 0
    }

}