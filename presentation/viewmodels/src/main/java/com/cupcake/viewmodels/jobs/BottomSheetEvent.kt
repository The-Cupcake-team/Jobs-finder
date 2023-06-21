package com.cupcake.viewmodels.jobs

sealed class BottomSheetEvent {

    data class OnShareClickListener(val id: String) : BottomSheetEvent()
     object OnSaveListener: BottomSheetEvent()

}