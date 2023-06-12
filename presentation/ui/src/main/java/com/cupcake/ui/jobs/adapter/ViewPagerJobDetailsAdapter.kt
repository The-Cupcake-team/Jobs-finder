package com.cupcake.ui.jobs.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

class ViewPagerJobDetailsAdapter(
    private val fragmentItems: Map<Int, Fragment>,
    fragmentManager: FragmentManager,
    lifecycle: Lifecycle,
) : FragmentStateAdapter(fragmentManager, lifecycle) {
    override fun getItemCount(): Int = fragmentItems.size
    override fun createFragment(position: Int): Fragment {
        return fragmentItems[position]!!
    }
}