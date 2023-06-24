package com.cupcake.ui.profile.resume

import android.os.Bundle
import com.cupcake.ui.R
import com.cupcake.ui.base.BaseFragment
import com.cupcake.ui.databinding.FragmentResumeBinding
import com.cupcake.viewmodels.profile.resume.ResumeViewModel

class ResumeFragment : BaseFragment<FragmentResumeBinding, ResumeViewModel>(
    R.layout.fragment_resume,
    ResumeViewModel::class.java
) {
    override val LOG_TAG: String="ResumeFragment"

    companion object {
        @JvmStatic
        fun newInstance() = ResumeFragment().apply {
            arguments = Bundle().apply {
            }
        }
    }
}