package com.cupcake.viewmodels.create_job

data class CreateJobUiState(
    val jobFormUiState: JobFormUiState = JobFormUiState(),
//    val activeIconToolBar: Int = R.drawable.ic_close,
//    val iconCloseToolBar: Int = R.drawable.ic_close,
//    val iconBackToolBar: Int = R.drawable.ic_arrow_down,
//    val titleToolBar: Int = R.string.next,
//    val formNumber: Int = R.string.page_number_one,
    val isLoading: Boolean = false,
    val isValidForm: Boolean = false,
    val error: String = "",
) {
    data class JobFormUiState(
        val idJobTitle: Int = 0,
        val company: String = "",
        val workType: String = "",
        val jobType: String = "",
        val jobLocation: String = "",
        val jobDescription: String = "",
        val salary: Double = 0.0,
    )
}