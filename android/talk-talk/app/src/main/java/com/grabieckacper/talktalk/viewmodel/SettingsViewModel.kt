package com.grabieckacper.talktalk.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.grabieckacper.talktalk.common.DataStoreConstants
import com.grabieckacper.talktalk.repository.DataStoreRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val _dataStoreRepository: DataStoreRepository
) : ViewModel() {
    data class SettingsViewModelState(
        val darkTheme: Boolean = false,
        val dynamicColor: Boolean = false
    )

    private val _state: MutableState<SettingsViewModelState> =
        mutableStateOf(SettingsViewModelState())
    val state: State<SettingsViewModelState>
        get() = _state

    init {
        viewModelScope.launch {
            _dataStoreRepository.read(
                key = booleanPreferencesKey(name = DataStoreConstants.DARK_THEME_KEY),
                defaultValue = false
            ).collect { darkTheme: Boolean ->
                _state.value = _state.value.copy(darkTheme = darkTheme)
            }
        }

        viewModelScope.launch {
            _dataStoreRepository.read(
                key = booleanPreferencesKey(name = DataStoreConstants.DYNAMIC_COLOR_KEY),
                defaultValue = false
            ).collect { dynamicColor: Boolean ->
                _state.value = _state.value.copy(dynamicColor = dynamicColor)
            }
        }
    }

    private fun <T> saveToDataStore(key: Preferences.Key<T>, value: T) {
        viewModelScope.launch {
            _dataStoreRepository.write(
                key = key,
                value = value
            )
        }
    }

    fun onDarkThemeChange(darkTheme: Boolean) {
        _state.value = _state.value.copy(darkTheme = darkTheme)

        saveToDataStore(
            key = booleanPreferencesKey(DataStoreConstants.DARK_THEME_KEY),
            value = _state.value.darkTheme
        )
    }

    fun onDynamicColorChange(dynamicColor: Boolean) {
        _state.value = _state.value.copy(dynamicColor = dynamicColor)

        saveToDataStore(
            key = booleanPreferencesKey(DataStoreConstants.DYNAMIC_COLOR_KEY),
            value = _state.value.dynamicColor
        )
    }

    fun clearData() {
        viewModelScope.launch {
            _dataStoreRepository.clear()
        }
    }
}
