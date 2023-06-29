package com.cupcake.jobsfinder.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "job_titles_table")
data class JobTitleEntity(
    @PrimaryKey
    val id: Int,
    val title: String
)