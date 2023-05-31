package com.cupcake.jobsfinder.ui.post

sealed interface PostUiEvent {
    data class ClickPostEvent(val id: Int) : PostUiEvent
}