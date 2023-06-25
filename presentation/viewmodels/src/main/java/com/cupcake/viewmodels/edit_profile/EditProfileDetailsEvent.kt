package com.cupcake.viewmodels.edit_profile

sealed class EditProfileDetailsEvent {
    object EditLocationClick : EditProfileDetailsEvent()
}