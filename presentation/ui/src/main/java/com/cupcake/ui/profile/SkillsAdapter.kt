package com.cupcake.ui.profile

import com.cupcake.ui.R
import com.cupcake.ui.base.BaseAdapter
import com.cupcake.viewmodels.profile.resume.skill.SkillsUiState
import com.cupcake.viewmodels.profile.resume.skill.SpecialSkillsInteractionListener

class SkillsAdapter (items: List<SkillsUiState>, listener : SpecialSkillsInteractionListener):
    BaseAdapter<SkillsUiState>(items, listener) {
    override var layoutId = R.layout.item_skills_profile
    override fun areItemsEqual(oldItem: SkillsUiState, newItem: SkillsUiState): Boolean {
        return oldItem.id == newItem.id
    }

}