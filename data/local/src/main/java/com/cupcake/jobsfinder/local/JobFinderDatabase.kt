package com.cupcake.jobsfinder.local


import androidx.room.Database
import androidx.room.RoomDatabase
import com.cupcake.jobsfinder.local.daos.JobFinderDao
import com.cupcake.jobsfinder.local.entities.JobsEntity

@Database(entities = [JobsEntity::class], version = 1)
abstract class JobFinderDatabase: RoomDatabase() {

    abstract fun getJobFinderDao(): JobFinderDao
}