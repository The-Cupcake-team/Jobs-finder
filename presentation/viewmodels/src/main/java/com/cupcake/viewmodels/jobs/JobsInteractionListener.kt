package com.cupcake.viewmodels.jobs

import com.cupcake.viewmodels.BuildConfig
import com.cupcake.viewmodels.base.BaseInteractionListener

interface JobsListener : BaseInteractionListener {
    fun onCardClickListener(id: String)
    fun onFloatingActionClickListener()
    fun onImageViewMoreClickListener(model:JobUiState)

    fun onChipClickListener(jobTitle: String)

    fun onSearchBoxClickListener()
}