package com.cupcake.viewmodels.jobs

import com.cupcake.viewmodels.base.BaseInteractionListener

interface JobsListener : BaseInteractionListener {
    fun onItemClickListener(id: String)
}