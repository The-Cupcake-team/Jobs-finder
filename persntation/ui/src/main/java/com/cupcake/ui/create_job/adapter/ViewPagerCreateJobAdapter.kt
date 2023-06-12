package com.cupcake.ui.create_job.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

class ViewPagerCreateJobAdapter(
    private val fragmentItems: Map<Int, Fragment>,
    fragmentManager: FragmentManager,
    lifecycle: Lifecycle,
) : FragmentStateAdapter(fragmentManager, lifecycle) {
    override fun getItemCount(): Int = fragmentItems.size
    override fun createFragment(position: Int): Fragment {
        return fragmentItems[position]!!
    }
}