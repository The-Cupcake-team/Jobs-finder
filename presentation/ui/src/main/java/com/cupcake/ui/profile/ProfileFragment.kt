package com.cupcake.ui.profile

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.cupcake.ui.R
import com.cupcake.ui.base.BaseFragment
import com.cupcake.ui.base.ViewPagerAdapter
import com.cupcake.ui.posts.FollowingFragment
import com.cupcake.ui.posts.PostsFragment
import com.cupcake.ui.posts.PublicFragment
import com.cupcake.ui.profile.jobs.ProfileJobsFragment
import com.cupcake.ui.profile.posts.ProfilePostFragment
import com.cupcake.viewmodels.profile.ProfileViewModel
import com.google.android.material.tabs.TabLayoutMediator

class ProfileFragment : BaseFragment<com.cupcake.ui.databinding.FragmentProfileBinding, ProfileViewModel>(
    R.layout.fragment_profile,
    ProfileViewModel::class.java

) {
    override val LOG_TAG: String="ProfileFragment"
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }
    private fun init() {
        val fragmentList = mutableListOf<Fragment>()
        val fragments = mapOf(
            RESUME_FRAGMENT to ProfileResumeFragment(),
            POSTS_FRAGMENT to ProfilePostFragment(),
            JOBS_FRAGMENT to ProfileJobsFragment(),
            ABOUT_FRAGMENT to ProfileResumeFragment()
        )
        fragmentList.clear()

        for (fragment in fragments.values) {
            fragmentList.add(fragment)
        }
        val adapter = ViewPagerAdapter(
            fragmentManager = childFragmentManager,
            fragmentList = fragmentList,
            lifecycle = lifecycle
        )

        binding.viewPager.adapter = adapter
        binding.viewPager.isUserInputEnabled = false
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            when (position) {
                0 -> tab.text = "Resume"
                1 -> tab.text = "Posts"
                2 -> tab.text = "Jobs"
                3 -> tab.text = "About"
            }
        }.attach()
    }

    companion object{
        const val RESUME_FRAGMENT = 0
        const val POSTS_FRAGMENT = 1
        const val JOBS_FRAGMENT = 2
        const val ABOUT_FRAGMENT = 3
    }
}