package com.cupcake.jobsfinder.di

import com.cupcake.jobsfinder.data.repository.RepositoryImpl
import com.cupcake.jobsfinder.domain.repository.Repository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun bindsRepository(repository: RepositoryImpl): Repository
}