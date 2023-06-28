package com.cupcake.ui.profile

import com.cupcake.ui.R
import com.cupcake.ui.base.BaseAdapter
import com.cupcake.viewmodels.profile.EducationUiState
import com.cupcake.viewmodels.profile.resume.education.SpecialEducationInteractionListener

class EducationAdapter (items: List<EducationUiState>, listener : SpecialEducationInteractionListener):
    BaseAdapter<EducationUiState>(items, listener) {
    override var layoutId = R.layout.item_education_profile
    override fun areItemsEqual(oldItem: EducationUiState, newItem: EducationUiState): Boolean {
        return oldItem.id == newItem.id
    }

}