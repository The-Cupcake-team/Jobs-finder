package com.cupcake.ui.profile.jobs.adapter

import com.cupcake.ui.R
import com.cupcake.ui.base.BaseAdapter
import com.cupcake.viewmodels.profile.jobs.ProfileJobListener
import com.cupcake.viewmodels.profile.jobs.ProfileJobUiState

class JobsProfileHorizontalAdapter(items: List<ProfileJobUiState>, listener: ProfileJobListener) :
    BaseAdapter<ProfileJobUiState>(items, listener) {
    override var layoutId: Int = R.layout.item_profile_saved_jobs
}