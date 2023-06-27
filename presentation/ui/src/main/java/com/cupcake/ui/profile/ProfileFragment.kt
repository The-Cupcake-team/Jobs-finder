package com.cupcake.ui.profile

import android.os.Bundle
import android.view.View
import com.cupcake.ui.R
import com.cupcake.ui.base.BaseFragment
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
        val adapter = ProfileFragmentPageAdapter(
            requireActivity().supportFragmentManager,
            lifecycle,
        )
        binding.viewPager.adapter = adapter
        binding.viewPager.setUserInputEnabled(false)
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            when (position) {
                0 -> tab.text = "Resume"
                1 -> tab.text = "Posts"
                2 -> tab.text = "Jobs"
                3 -> tab.text = "About"
            }
        }.attach()

    }
}