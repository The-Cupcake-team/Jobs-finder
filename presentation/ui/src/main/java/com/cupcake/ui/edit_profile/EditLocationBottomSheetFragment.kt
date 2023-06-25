package com.cupcake.ui.edit_profile

import com.cupcake.ui.R
import com.cupcake.ui.base.BaseBottomSheetFragment
import com.cupcake.ui.databinding.FragmentEditLocationBottomSheetBinding
import com.cupcake.viewmodels.edit_profile.bottomsheet.EditLocationBottomSheetViewModel


class EditLocationBottomSheetFragment :
    BaseBottomSheetFragment<FragmentEditLocationBottomSheetBinding, EditLocationBottomSheetViewModel>(
        R.layout.fragment_edit_location_bottom_sheet,
        EditLocationBottomSheetViewModel::class.java
    ) {
    override val LOG_TAG: String = this.javaClass.name
}