package com.cupcake.jobsfinder.local.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.cupcake.jobsfinder.local.entities.JobsEntity

@Dao
interface JobFinderDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertJop(job: JobsEntity)

    @Query("SELECT * FROM jobs_table WHERE id = :id")
    fun getJopById(id: String): JobsEntity?

    @Delete
    fun deleteSavedJob(job: JobsEntity)
}