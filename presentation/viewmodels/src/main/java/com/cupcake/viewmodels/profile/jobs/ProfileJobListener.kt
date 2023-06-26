package com.cupcake.viewmodels.profile.jobs

import com.cupcake.viewmodels.base.BaseInteractionListener

interface ProfileJobListener: BaseInteractionListener {

    fun onCardClickListener(id: String)

    fun onImageViewMoreClickListener(model: com.cupcake.viewmodels.profile.jobs.ProfileJobUiState)


}