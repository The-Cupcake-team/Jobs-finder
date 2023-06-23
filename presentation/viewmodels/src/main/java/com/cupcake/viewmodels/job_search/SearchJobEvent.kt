package com.cupcake.viewmodels.job_search


sealed class SearchJobEvent {
    data class JobCardClick(val id: String) : SearchJobEvent()

    data class OnMoreOptionClickListener(val model:JopSearchUIState): SearchJobEvent()
}