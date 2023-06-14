package com.cupcake.ui.jobs.adapter

import android.annotation.SuppressLint
import android.view.ViewGroup
import com.cupcake.ui.BR
import com.cupcake.ui.R
import com.cupcake.ui.base.BaseActionListener
import com.cupcake.ui.base.BaseAdapter
import com.cupcake.ui.jobs.JobsItem
import com.cupcake.viewmodels.jobs.JobTitleUiState
import com.cupcake.viewmodels.jobs.JobUiState


class JobsAdapter(items: List<JobsItem>, private val listener: JobsListener) :
    BaseAdapter<JobsItem>(items, listener) {

    override var layoutId: Int = 0

    @SuppressLint("NotifyDataSetChanged")
    fun setJobsItems(newItems: List<JobsItem>) {
        setData(newItems.sortedBy { it.rank })
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        layoutId = viewType
        return super.onCreateViewHolder(parent, viewType)
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        when (val current = getItems()[position]) {
            is JobsItem.PopularJobs -> bindPopularJobs(current.items, holder as ItemViewHolder)
            is JobsItem.Recommended -> bindRecommendedJobs(current.items, holder as ItemViewHolder)
            is JobsItem.TopSalary -> bindTopSalaryJobs(current.items, holder as ItemViewHolder)
            is JobsItem.LocationJobs -> bindInLocationJobs(current.items, holder as ItemViewHolder)
        }
    }

    private fun bindPopularJobs(items: List<JobTitleUiState>, holder: ItemViewHolder) {
        holder.binding.setVariable(BR.adapter, PopularJobsAdapter(items, listener))
    }

    private fun bindRecommendedJobs(
        items: List<JobUiState>,
        holder: ItemViewHolder
    ) {
        holder.binding.setVariable(BR.adapter, RecommendedJobsAdapter(items, listener))
    }

    private fun bindTopSalaryJobs(
        items: List<JobUiState>,
        holder: ItemViewHolder
    ) {
        holder.binding.setVariable(BR.adapter, TopSalaryJobsAdapter(items, listener))
    }

    private fun bindInLocationJobs(
        items: List<JobUiState>,
        holder: ItemViewHolder
    ) {
        holder.binding.setVariable(BR.adapter, JobsOnLocationAdapter(items, listener))
    }

    override fun getItemViewType(position: Int): Int {
        return when (getItems()[position]) {
            is JobsItem.PopularJobs -> POPULAR_JOBS
            is JobsItem.Recommended -> RECOMMENDED_JOBS
            is JobsItem.TopSalary -> TOP_SALARY_JOBS
            is JobsItem.LocationJobs -> IN_LOCATION_JOBS
        }
    }

    companion object {
        val RECOMMENDED_JOBS = R.layout.recommended_jobs_recycler
        val TOP_SALARY_JOBS = R.layout.top_salary_recycler
        val IN_LOCATION_JOBS = R.layout.job_on_location_recycler
        val POPULAR_JOBS = R.layout.popular_jobs_recycler
    }

    interface JobsListener : BaseActionListener {
        fun onItemClickListener(id: String)
    }
}