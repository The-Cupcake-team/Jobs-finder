package com.cupcake.ui.profile.jobs

import android.view.View
import android.view.ViewGroup
import com.cupcake.ui.R
import com.cupcake.ui.base.BaseFragment
import com.cupcake.ui.databinding.FragmentJobProfileSeeAllSavedBinding
import com.cupcake.viewmodels.profile.jobs.ProfileJobsViewModel


class JobProfileSeeAllSavedFragment :
    BaseFragment<FragmentJobProfileSeeAllSavedBinding, ProfileJobsViewModel>(
        R.layout.fragment_job_profile_see_all_saved,
        ProfileJobsViewModel::class.java
    ) {
    override val LOG_TAG: String = this.javaClass.name


}