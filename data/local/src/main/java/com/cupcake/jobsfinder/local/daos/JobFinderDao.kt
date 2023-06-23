package com.cupcake.jobsfinder.local.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.cupcake.jobsfinder.local.entities.JobTitleEntity
import com.cupcake.jobsfinder.local.entities.JobsEntity

@Dao
interface JobFinderDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertJob(job: JobsEntity)

    @Query("SELECT * FROM jobs_table WHERE id = :id")
    fun getJopById(id: String): JobsEntity?

    @Delete
    fun deleteSavedJob(job: JobsEntity)


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertJobTitles(jobsTitle: JobTitleEntity)

    @Query("SELECT * FROM job_titles_table")
    fun getJobTitles(): List<JobTitleEntity>
}