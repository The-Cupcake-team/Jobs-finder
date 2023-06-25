package com.cupcake.jobsfinder.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "profile_table")
data class ProfileEntity(
    @PrimaryKey
    val id: String,
    var avatar: String,
    var fullName: String,
    var jobTitles: String,
    var location: String,
    var linkWebsite: String,
)

