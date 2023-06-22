package com.cupcake.ui.job_search

import android.os.Bundle
import android.view.View
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.cupcake.ui.R
import com.cupcake.ui.base.BaseFragment
import com.cupcake.ui.databinding.BottomSheetJobsSearchFilterBinding
import com.cupcake.ui.databinding.FragmentJobSearchBinding
import com.cupcake.viewmodels.job_search.JobSearchViewModel
import com.google.android.material.bottomsheet.BottomSheetDialog


class JobSearchFragment : BaseFragment<FragmentJobSearchBinding, JobSearchViewModel>(
    R.layout.fragment_job_search,
    JobSearchViewModel::class.java
) {

    override val LOG_TAG: String = this::class.java.name

    private val args: JobSearchFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        onBackButtonClicked()
        binding.editTextSearchInput.setText(args.jobTitle)

    }

    override fun onResume() {
        super.onResume()
        binding.imageViewFilter.setOnClickListener {
            showFilterSheet()
        }
    }

    private fun onBackButtonClicked(){
        binding.buttonBack.setOnClickListener {view ->
            Navigation.findNavController(view).popBackStack()
        }
    }




    private fun showFilterSheet() {
        val bottomSheetBinding = BottomSheetJobsSearchFilterBinding.inflate(layoutInflater)
        val dialog = BottomSheetDialog(requireContext())

        dialog.apply {
            setContentView(bottomSheetBinding.root)
            show()
        }
    }


}