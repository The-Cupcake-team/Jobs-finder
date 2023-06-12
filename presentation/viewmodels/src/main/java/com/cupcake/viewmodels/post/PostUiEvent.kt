package com.cupcake.viewmodels.post

sealed interface PostUiEvent {
    data class ClickPostEvent(val id: Int) : PostUiEvent
}