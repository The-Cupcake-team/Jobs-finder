package com.cupcake.jobsfinder.local


import androidx.room.Database
import androidx.room.RoomDatabase
import com.cupcake.jobsfinder.local.daos.JobFinderDao
import com.cupcake.jobsfinder.local.entities.JobTitleEntity
import com.cupcake.jobsfinder.local.entities.JobsEntity
import com.cupcake.jobsfinder.local.entities.PostsEntity
import com.cupcake.jobsfinder.local.entities.ProfileEntity

@Database(
    entities = [
        JobsEntity::class,
        PostsEntity::class,
        ProfileEntity::class,
        JobTitleEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class JobFinderDatabase: RoomDatabase() {

    abstract fun getJobFinderDao(): JobFinderDao
}