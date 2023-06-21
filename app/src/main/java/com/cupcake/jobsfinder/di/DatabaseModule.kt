package com.cupcake.jobsfinder.di

import android.content.Context
import androidx.room.Room
import com.cupcake.jobsfinder.local.JobFinderDatabase
import com.cupcake.jobsfinder.local.daos.JobFinderDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context,
        @Named("databaseName") databaseName: String
    ): JobFinderDatabase {
        return Room
            .databaseBuilder(context, JobFinderDatabase::class.java, databaseName)
            .build()
    }

    @Singleton
    @Provides
    @Named("databaseName")
    fun provideDataBaseName(): String = "job_finder_db"

    @Provides
    @Singleton
    fun provideMarvelDao(marvelDatabase: JobFinderDatabase): JobFinderDao = marvelDatabase.getJobFinderDao()

}
