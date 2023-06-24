package com.cupcake.jobsfinder.local.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class ProfileDataStore @Inject constructor(@ApplicationContext context: Context) {

    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = DATASTORE_NAME)

    private val dataStore = context.dataStore

    suspend fun saveProfileData(avatarUri: String, jobTitle: Int) {
        dataStore.edit { preferences ->
            preferences[AVATAR_URI] = avatarUri
            preferences[JOB_TITLE] = jobTitle
        }
    }

    suspend fun getAvatarUri(): String? {
        val preferences = dataStore.data.first()
        return preferences[AVATAR_URI]
    }

    suspend fun getJobTitle(): Int? {
        val preferences = dataStore.data.first()
        return preferences[JOB_TITLE]
    }

    suspend fun clearProfileData() {
        dataStore.edit { preferences ->
            preferences.clear()
        }
    }

    companion object {
        const val DATASTORE_NAME = "profile"
        val AVATAR_URI = stringPreferencesKey("image_url")
        val JOB_TITLE = intPreferencesKey("job_title")

    }
}