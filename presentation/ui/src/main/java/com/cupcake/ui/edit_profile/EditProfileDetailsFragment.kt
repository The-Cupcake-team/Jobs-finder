package com.cupcake.ui.edit_profile

import android.os.Bundle
import android.view.View
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.cupcake.ui.R
import com.cupcake.ui.base.BaseFragment
import com.cupcake.ui.databinding.FragmentEditProfileDetailsBinding
import com.cupcake.viewmodels.edit_profile.EditProfileDetailsEvent
import com.cupcake.viewmodels.edit_profile.EditProfileDetailsViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EditProfileDetailsFragment :
    BaseFragment<FragmentEditProfileDetailsBinding, EditProfileDetailsViewModel>(
        R.layout.fragment_edit_profile_details,
        EditProfileDetailsViewModel::class.java
    ) {
    override val LOG_TAG: String = this.javaClass.name

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        handleEvents()
    }

    private fun handleEvents() {
        lifecycleScope.launch(Dispatchers.Main) {
            viewModel.event.collect {
                when (it) {
                    is EditProfileDetailsEvent.EditLocationClick -> navigateToEditLocationBottomSheet()

                }
            }
        }
    }

    private fun navigateToEditLocationBottomSheet() {
        findNavController().navigate(EditProfileDetailsFragmentDirections.actionEditProfileDetailsFragmentToEditLocationBottomSheetFragment())
    }
}