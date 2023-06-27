package com.cupcake.ui.profile

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.cupcake.ui.R
import com.cupcake.ui.base.BaseFragment
import com.cupcake.ui.databinding.FragmentProfileEducationBinding
import com.cupcake.viewmodels.profile.EducationUiState
import com.cupcake.viewmodels.profile.resume.ProfileEducationViewModel
import com.cupcake.viewmodels.profile.SaveEvent
import kotlinx.coroutines.launch


class ProfileEducationFragment : BaseFragment<FragmentProfileEducationBinding, ProfileEducationViewModel>(
    R.layout.fragment_profile_education,
    ProfileEducationViewModel::class.java
) {
    override val LOG_TAG: String
        get() = TODO("Not yet implemented")

    private val args: ProfileEducationFragmentArgs by navArgs()

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
        Toast.makeText(requireContext(), "Education Added Successfully", Toast.LENGTH_SHORT).show()
    }

    private fun showUpdatedToast() {
        Toast.makeText(requireContext(), "Education Updated Successfully", Toast.LENGTH_SHORT).show()
    }

    override fun onResume() {
        super.onResume()
        viewModel.updateMode(args.fromAddButton, args.education?: EducationUiState())
    }
}