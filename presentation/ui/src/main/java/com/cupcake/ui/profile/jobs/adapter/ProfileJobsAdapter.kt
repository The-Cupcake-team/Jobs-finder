package com.cupcake.ui.profile.jobs.adapter

import android.annotation.SuppressLint
import android.view.View
import android.view.ViewGroup
import com.cupcake.ui.BR
import com.cupcake.ui.R
import com.cupcake.ui.profile.jobs.ProfileJobsItem
import com.cupcake.viewmodels.profile.jobs.ProfileJobListener
import com.cupcake.viewmodels.profile.jobs.ProfileJobUiState

class ProfileJobsAdapter(item: List<ProfileJobsItem>, private val listener: ProfileJobListener) :
    com.cupcake.ui.base.BaseAdapter<ProfileJobsItem>(item, listener)
{
    override var layoutId: Int = 0
    @SuppressLint("NotifyDataSetChanged")
    fun setProfileJobsItems(newItems: List<ProfileJobsItem>) {
        setData(newItems)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        layoutId = viewType
        return super.onCreateViewHolder(parent, viewType)
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        when (val current = getItems()[position]) {
            is ProfileJobsItem.RecentJobs -> bindRecentJobs(current.items, holder as ItemViewHolder)
            is ProfileJobsItem.SavedJobs -> bindSavedJobs(current.items, holder as ItemViewHolder)
        }
    }

    private fun bindRecentJobs(items: List<ProfileJobUiState>, holder: ItemViewHolder) {
        val adapter = RecentJobs(items, listener)
        holder.binding.setVariable(BR.adapter, adapter)
    }

        private fun bindSavedJobs(items: List<ProfileJobUiState>, holder: ItemViewHolder) {
        holder.binding.setVariable(BR.adapter, SavedJobs(items, listener))
    }

    override fun getItemViewType(position: Int): Int {
        return when (getItems()[position]) {
            is ProfileJobsItem.RecentJobs -> RECENT_JOBS
            is ProfileJobsItem.SavedJobs -> SAVED_JOBS
        }
    }

    private fun hiddenRootIfEmpty(root: View, list: List<Any>){
        root.visibility = if (list.isNotEmpty()) View.VISIBLE else View.GONE
    }

    companion object {
        val RECENT_JOBS = R.layout.fragment_recent_job
        val SAVED_JOBS = R.layout.fragment_saved_job
    }


}