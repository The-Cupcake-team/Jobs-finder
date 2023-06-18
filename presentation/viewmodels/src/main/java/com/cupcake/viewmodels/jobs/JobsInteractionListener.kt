package com.cupcake.viewmodels.jobs

import com.cupcake.viewmodels.base.BaseInteractionListener

interface JobsListener : BaseInteractionListener {
    fun onCardClickListener(id: String)
    fun onChipClickListener(jobTitle: String)

    fun onSearchBoxClickListener()
}