package com.merttoptas.shoppingcompose.core.data.local.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

/**
 * Created by mertcantoptas on 26.05.2023
 */

class DataStoreManager(private val dataStore: DataStore<Preferences>) {
    companion object {
        val KEY_ACCESS_TOKEN = stringPreferencesKey("access_token")
        private const val PREFS_NAME = "shopping_prefs"
        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = PREFS_NAME)
    }

    suspend fun saveToken(token: String) {
        dataStore.edit { prefs ->
            prefs[KEY_ACCESS_TOKEN] = token
        }
    }

     fun getToken(): Flow<String>? {
        dataStore.data.map { prefs ->
            prefs[KEY_ACCESS_TOKEN] ?: ""
        }
        return null
    }
}