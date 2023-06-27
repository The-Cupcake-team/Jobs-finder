package com.cupcake.ui.profile.resume

import com.cupcake.ui.R
import com.cupcake.ui.base.BaseAdapter
import com.cupcake.viewmodels.profile.EducationUiState
import com.cupcake.viewmodels.profile.resume.SkillsUiState
import com.cupcake.viewmodels.profile.resume.SpecialEducationInteractionListener
import com.cupcake.viewmodels.profile.resume.SpecialSkillsInteractionListener

class SkillsAdapter (items: List<SkillsUiState>, listener : SpecialSkillsInteractionListener):
    BaseAdapter<SkillsUiState>(items, listener) {
    override var layoutId = R.layout.item_skills_profile
    override fun areItemsEqual(oldItem: SkillsUiState, newItem: SkillsUiState): Boolean {
        return oldItem.id == newItem.id
    }

}