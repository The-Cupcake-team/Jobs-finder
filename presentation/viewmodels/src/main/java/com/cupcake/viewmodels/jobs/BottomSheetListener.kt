package com.cupcake.viewmodels.jobs

interface BottomSheetListener {
    fun onShareClickListener( id : String)

    fun onSaveListener(model:JobUiState)
}