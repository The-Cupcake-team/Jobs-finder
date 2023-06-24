package com.cupcake.jobsfinder.local.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.cupcake.jobsfinder.local.entities.JobsEntity
import com.cupcake.jobsfinder.local.entities.PostsEntity

@Dao
interface JobFinderDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertJob(job: JobsEntity)

    @Query("SELECT * FROM jobs_table WHERE id = :id")
    fun getJopById(id: String): JobsEntity?

    @Delete
    fun deleteSavedJob(job: JobsEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPost(Post: PostsEntity)

    @Query("SELECT * FROM posts_table WHERE id = :id")
    fun getPostById(id: String): PostsEntity?

    @Delete
    fun deleteSavedPost(Post: PostsEntity)
}