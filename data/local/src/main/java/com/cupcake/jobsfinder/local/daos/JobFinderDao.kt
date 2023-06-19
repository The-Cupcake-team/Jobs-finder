package com.cupcake.jobsfinder.local.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.cupcake.jobsfinder.local.entities.JobsEntity

@Dao
interface JobFinderDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertJop(job: JobsEntity)
}