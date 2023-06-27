package com.cupcake.ui.profile.about_me

import android.os.Bundle
import android.view.View
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import com.cupcake.ui.R
import com.cupcake.ui.base.BaseFragment
import com.cupcake.ui.databinding.FragmentAboutMeProfileBinding
import com.cupcake.viewmodels.profile.about_me.AboutMeProfileViewModel


class AboutMeProfileFragment : BaseFragment<FragmentAboutMeProfileBinding, AboutMeProfileViewModel>(
    R.layout.fragment_about_me_profile ,
    AboutMeProfileViewModel::class.java
) {
    override val LOG_TAG: String =this.javaClass.name
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navigateToEditAboutFragment()
    }

    private fun navigateToDirection(directions: NavDirections) {
        findNavController().navigate(directions)
    }
    private fun navigateToEditAboutFragment() {
        binding.textViewTitleEditClick.setOnClickListener {
//        navigateToDirection(
//            AboutMeProfileFragmentDirections.actionAboutMeProfileFragmentToEditAboutMeProfileFragment()
//        )
    }
    }

}
