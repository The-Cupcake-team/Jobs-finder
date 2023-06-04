package com.cupcake.jobsfinder.di

import com.cupcake.jobsfinder.data.remote.JobApiService
import com.cupcake.jobsfinder.data.repository.Repository
import com.cupcake.jobsfinder.data.repository.RepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.SerialInfo
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun bindsRepository(repository: RepositoryImpl): Repository

    companion object {
        @JvmStatic
        @Provides
        fun provideRepository(findJobApi: JobApiService): RepositoryImpl {
            return RepositoryImpl(findJobApi)
        }
    }

}