package com.cupcake.ui.profile

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.navArgs
import com.cupcake.ui.R
import com.cupcake.ui.base.BaseFragment
import com.cupcake.ui.databinding.FragmentProfileEducationBinding
import com.cupcake.viewmodels.profile.ProfileEducationViewModel

class ProfileEducationFragment : BaseFragment<FragmentProfileEducationBinding, ProfileEducationViewModel>(
    R.layout.fragment_profile_education,
    ProfileEducationViewModel::class.java
) {
    override val LOG_TAG: String
        get() = TODO("Not yet implemented")

    private val args: ProfileEducationFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

    }

    override fun onResume() {
        super.onResume()
        viewModel.updateMode(args.fromAddButton, args.id ?: "")
    }
}