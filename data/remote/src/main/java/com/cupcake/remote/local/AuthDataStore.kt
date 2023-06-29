package com.cupcake.remote.local


import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

class AuthDataStore @Inject constructor(
    private val dataStore: DataStore<Preferences>
) {

    suspend fun saveAuthData(authToken: String, expireTime: Long) {
        dataStore.edit { preferences ->
            preferences[TOKEN] = authToken
            preferences[EXPIRE_TIME] = expireTime
        }
    }

    fun getAuthToken(): String? {
        return runBlocking {
            dataStore.data.map { it[TOKEN] }.first()
        }
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
        val TOKEN = stringPreferencesKey("token")
        val EXPIRE_TIME = longPreferencesKey("expire_time")
    }
}

