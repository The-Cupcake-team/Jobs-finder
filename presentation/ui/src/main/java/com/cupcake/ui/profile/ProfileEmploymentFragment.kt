package com.cupcake.ui.profile

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.cupcake.ui.R
import com.cupcake.ui.base.BaseFragment
import com.cupcake.ui.databinding.FragmentProfileEmploymentBinding
import com.cupcake.viewmodels.profile.EducationUiState
import com.cupcake.viewmodels.profile.EmploymentUiState
import com.cupcake.viewmodels.profile.ProfileEmploymentViewModel
import com.cupcake.viewmodels.profile.resume.education.SaveEvent
import kotlinx.coroutines.launch


class ProfileEmploymentFragment : BaseFragment<FragmentProfileEmploymentBinding, ProfileEmploymentViewModel>(
    R.layout.fragment_profile_employment,
    ProfileEmploymentViewModel::class.java
){
    override val LOG_TAG: String
        get() = TODO("Not yet implemented")

    private val args: ProfileEmploymentFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        observeSaveEvent()
    }

    private fun observeSaveEvent() {
        lifecycleScope.launch {
            viewModel.event.collect{
                handleSaveEvent(it)
            }
        }
    }

    private fun handleSaveEvent(event: SaveEvent) {
        when(event){
            SaveEvent.Added -> showAddedToast()
            SaveEvent.Error -> showToastError()
            SaveEvent.Updated -> showUpdatedToast()
        }
    }

    private fun showToastError() {
        Toast.makeText(requireContext(), "Some thing went wrong", Toast.LENGTH_LONG).show()
    }

    private fun showAddedToast() {
        Toast.makeText(requireContext(), "Employment Added Successfully", Toast.LENGTH_SHORT).show()
    }

    private fun showUpdatedToast() {
        Toast.makeText(requireContext(), "Employment Updated Successfully", Toast.LENGTH_SHORT).show()
    }

    override fun onResume() {
        super.onResume()
        viewModel.updateMode(args.fromAddButton, args.employment?: EmploymentUiState())
    }
}