package com.revup.features.auth.data.local

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class UserPreferencesDatasource @Inject constructor(
    private val dataStore: DataStore<Preferences>
) {
    private companion object{
        val TOKEN = stringPreferencesKey("token")
        val NAME = stringPreferencesKey("name")
    }
    val token: Flow<String?> = dataStore.data.map { it[TOKEN] }
    val name: Flow<String?> = dataStore.data.map { it[NAME] }

    suspend fun clearSession(){
        dataStore.edit {
            it.remove(TOKEN)
            it.remove(NAME)
        }
    }

    suspend fun saveSession(token: String, name: String){
        dataStore.edit {
            it[TOKEN] = token
            it[NAME] = name
        }
    }

}
