package com.cupcake.jobsfinder.ui.create_job.state

import com.cupcake.jobsfinder.R

data class CreateJobUiState(
    val jobFormUiState: JobFormUiState = JobFormUiState(),
    val activeIconToolBar: Int = R.drawable.ic_close,
    val iconCloseToolBar: Int = R.drawable.ic_close,
    val iconBackToolBar: Int = R.drawable.ic_arrow_down,
    val titleToolBar: Int = R.string.next,
    val formNumber: Int = R.string.page_number_one,
    val isLoading: Boolean = false,
    val isValidForm: Boolean = false,
    val error: String = "",
)