package com.cupcake.viewmodels.profile

sealed class SaveEvent{
    object Added: SaveEvent()
    object Updated: SaveEvent()
    object Error: SaveEvent()
}
