package com.cupcake.viewmodels.job_search


sealed class SearchJobEvent {
    data class JobCardClick(val id: String) : SearchJobEvent()

    object OnFilterClicked : SearchJobEvent()

    object OnApplyButtonClicked : SearchJobEvent()

    object OnClearButtonClicked : SearchJobEvent()

    data class OnMoreOptionClickListener(val model:JopSearchUIState): SearchJobEvent()
}