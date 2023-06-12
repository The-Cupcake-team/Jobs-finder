package com.cupcake.ui.post

sealed interface PostUiEvent {
    data class ClickPostEvent(val id: Int) : PostUiEvent
}