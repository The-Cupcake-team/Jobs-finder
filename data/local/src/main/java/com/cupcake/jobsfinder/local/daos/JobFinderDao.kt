package com.cupcake.jobsfinder.local.daos

import androidx.room.Dao
import androidx.room.Insert
import com.cupcake.jobsfinder.local.entities.JobsEntity

@Dao
interface JobFinderDao {
    @Insert
    fun insertJop(job: JobsEntity)
}