package com.cupcake.ui.jobs

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.Window
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import androidx.viewpager2.widget.ViewPager2
import com.cupcake.ui.R
import com.cupcake.ui.base.BaseFragment
import com.cupcake.ui.databinding.DialogBinding
import com.cupcake.ui.databinding.FragmentJobDetailsBinding
import com.cupcake.ui.jobs.adapter.ViewPagerJobDetailsAdapter
import com.cupcake.viewmodels.job_details.JobViewModel
import com.cupcake.viewmodels.job_details.JobsDetailsUiState
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject


class JobDetailsFragment @Inject constructor(
): BaseFragment<FragmentJobDetailsBinding, JobViewModel>(
    R.layout.fragment_job_details,
    JobViewModel::class.java
) {
    override val LOG_TAG: String = this.javaClass.simpleName
    private val args: JobDetailsFragmentArgs by navArgs()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setJobAdapter()
        setViewModel()
        applyClickHandler()
        onClickBackNavigationIcon()
    }

    private fun setViewModel(){
        viewModel.getJobDetails(args.jobId.toString())
        viewModel.jobId = args.jobId.toString()
    }
    private fun setJobAdapter(){
        val fragmentTasks = mapOf(
            ABOUT_JOB_FRAGMENT to AboutJobCategory(),
            ABOUT_CATEGORY_FRAGMENT to FragmentAboutCompanyCategory(),
        )
        val adapter = ViewPagerJobDetailsAdapter(
            fragmentManager = requireActivity().supportFragmentManager,
            fragmentItems = fragmentTasks,
            lifecycle = lifecycle,
        )
        binding.apply {
            viewPagerCategory.adapter = adapter
            setTabLayout(tabLayoutCategory, viewPagerCategory)
        }
    }

    private fun setTabLayout(tabLayoutCategory : TabLayout, viewPagerCategory : ViewPager2){
        val tabName = listOf("About job", " About company")
        TabLayoutMediator(tabLayoutCategory ,viewPagerCategory){ tab ,position ->
            tab.text= tabName[position]
        }.attach()
    }

    private fun applyClickHandler(){
        binding.buttonApplyJob.setOnClickListener {
            setDialog()
        }
    }
    private fun setDialog() {
        val alertBuilder = AlertDialog.Builder(requireContext())
        val binding = DialogBinding.inflate(LayoutInflater.from(requireContext()))
        alertBuilder.setView(binding.root)
        val alertDialog = alertBuilder.create()
        alertDialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        alertDialog.setCancelable(false)
        alertDialog.show()

        binding.applyButton.setOnClickListener {
            val jobDetails = viewModel.state.value.job
            sendEmail(jobDetails)
            alertDialog.dismiss()
        }

        binding.cancelButton.setOnClickListener {
            alertDialog.dismiss()
        }
    }

    private fun sendEmail(jobDetails: JobsDetailsUiState) {
            val intent = Intent(Intent.ACTION_SENDTO).apply {
                data = Uri.parse("mailto:${jobDetails.companyName}@gmail.com")
                putExtra(Intent.EXTRA_SUBJECT, jobDetails.title)
        }
        requireActivity().startActivity(intent)
    }

    companion object{
        const val ABOUT_JOB_FRAGMENT = 0
        const val ABOUT_CATEGORY_FRAGMENT = 1
    }

    private fun onClickBackNavigationIcon() {
        binding.toolBar.setNavigationOnClickListener { view ->
            view.findNavController().popBackStack()
        }
    }

}


