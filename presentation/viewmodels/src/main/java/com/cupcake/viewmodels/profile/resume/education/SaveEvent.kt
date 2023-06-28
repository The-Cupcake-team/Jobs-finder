package com.cupcake.viewmodels.profile.resume.education

sealed class SaveEvent{
    object Added: SaveEvent()
    object Updated: SaveEvent()
    object Error: SaveEvent()
}
