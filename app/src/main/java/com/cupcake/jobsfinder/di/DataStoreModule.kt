package com.cupcake.jobsfinder.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import dagger.Module
import dagger.Provides
import androidx.datastore.preferences.preferencesDataStoreFile
import com.cupcake.remote.local.AuthDataStore
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DataStoreModule {

    @Provides
    @Singleton
    fun provideDataStore(
        @ApplicationContext context: Context,
        @Named("DATA_STORE_NAME") dataStoreName: String
    ): DataStore<Preferences> {
        return PreferenceDataStoreFactory.create {
            context.preferencesDataStoreFile(dataStoreName)
        }
    }

    @Provides
    @Singleton
    fun provideAuthDataStore(dataStore: DataStore<Preferences>): AuthDataStore {
        return AuthDataStore(dataStore)
    }

    @Provides
    @Singleton
    @Named("DATA_STORE_NAME")
    fun provideDataStoreName(): String = "auth"
}