package com.cupcake.viewmodels.job_search

import com.cupcake.viewmodels.base.BaseInteractionListener

interface JobsSearchInteractionListener : BaseInteractionListener {
    fun onCardClickListener(id: String)
}