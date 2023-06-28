package com.cupcake.ui.profile.about_me

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.cupcake.ui.R
import com.cupcake.ui.base.BaseFragment
import com.cupcake.ui.databinding.FragmentAboutMeProfileBinding
import com.cupcake.ui.databinding.FragmentEditAboutMeProfileBinding
import com.cupcake.viewmodels.profile.about_me.AboutMeProfileViewModel


class EditAboutMeProfileFragment : BaseFragment<FragmentEditAboutMeProfileBinding, AboutMeProfileViewModel>(
    R.layout.fragment_edit_about_me_profile ,
    AboutMeProfileViewModel::class.java
) {
    override val LOG_TAG: String =this.javaClass.name
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onClickBackNavigationIcon()
    }

    private fun onClickBackNavigationIcon() {
        binding.toolBar.setNavigationOnClickListener { view ->
            view.findNavController().popBackStack()
        }
    }
}