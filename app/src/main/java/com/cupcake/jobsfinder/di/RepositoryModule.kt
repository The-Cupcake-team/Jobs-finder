package com.cupcake.jobsfinder.di

import com.cupcake.repository.JobFinderRepository
import com.cupcake.repository.JobFinderRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun bindsRepository(repository: JobFinderRepositoryImpl): JobFinderRepository
}