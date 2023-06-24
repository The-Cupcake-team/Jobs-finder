package com.cupcake.ui.profile

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.cupcake.ui.profile.resume.ResumeFragment

class ProfileFragmentPageAdapter(
    fragmentManager: FragmentManager,
    lifecycle: androidx.lifecycle.Lifecycle,
) : FragmentStateAdapter(fragmentManager, lifecycle) {
    override fun getItemCount(): Int {
        return 4
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> ResumeFragment.newInstance()
            1 -> ResumeFragment.newInstance()
            2 -> ResumeFragment.newInstance()
            else -> ResumeFragment.newInstance()

        }
    }
}