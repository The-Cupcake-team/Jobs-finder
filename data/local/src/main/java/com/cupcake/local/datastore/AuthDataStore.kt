package com.cupcake.local.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class AuthDataStore @Inject constructor(@ApplicationContext context: Context) {

    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = DATASTORE_NAME)

    private val dataStore = context.dataStore

    suspend fun saveAuthData(authToken: String, expireTime: Long) {
        dataStore.edit { preferences ->
            preferences[TOKEN] = authToken
            preferences[EXPIRE_TIME] = expireTime
        }
    }

    suspend fun getAuthToken(): String? {
        val preferences = dataStore.data.first()
        return preferences[TOKEN]
    }

    suspend fun getAuthTokenExpireTime(): Long? {
        val preferences = dataStore.data.first()
        return preferences[EXPIRE_TIME]
    }

    suspend fun clearAuthData() {
        dataStore.edit { preferences ->
            preferences.clear()
        }
    }

    companion object {
        const val DATASTORE_NAME = "auth"
        val TOKEN = stringPreferencesKey("token")
        val EXPIRE_TIME = longPreferencesKey("expire_time")
    }
}

