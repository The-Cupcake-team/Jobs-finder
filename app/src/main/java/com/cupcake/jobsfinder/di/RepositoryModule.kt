package com.cupcake.jobsfinder.di

import com.cupcake.repository.AuthenticationRepositoryImpl
import com.cupcake.repository.JobFinderRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import repo.AuthenticationRepository
import repo.JobFinderRepository


@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun bindsRepository(repository: JobFinderRepositoryImpl): JobFinderRepository

    @Binds
    abstract fun bindsAuthRepository(repository: AuthenticationRepositoryImpl): AuthenticationRepository
}