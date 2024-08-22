package com.grabieckacper.talktalk.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.MutablePreferences
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class DataStoreRepository @Inject constructor(private val _dataStore: DataStore<Preferences>) {
    fun <T> read(key: Preferences.Key<T>, defaultValue: T): Flow<T> {
        return _dataStore.data.map { value: Preferences ->
            val result = value[key] ?: defaultValue
            result
        }
    }

    suspend fun <T> write(key: Preferences.Key<T>, value: T) {
        _dataStore.edit { mutablePreferences: MutablePreferences ->
            mutablePreferences[key] = value
        }
    }

    suspend fun clear() {
        _dataStore.edit { mutablePreferences: MutablePreferences ->
            mutablePreferences.clear()
        }
    }
}
