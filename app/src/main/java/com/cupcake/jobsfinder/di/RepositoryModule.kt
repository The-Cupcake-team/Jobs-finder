package com.cupcake.jobsfinder.di

import com.cupcake.jobsfinder.domain.reposirory.Repository
import com.cupcake.jobsfinder.data.repository.RepositoryImpl
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