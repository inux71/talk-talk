package com.grabieckacper.talktalk.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.grabieckacper.talktalk.common.DataStoreConstants
import com.grabieckacper.talktalk.repository.DataStoreRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val _dataStoreRepository: DataStoreRepository
) : ViewModel() {
    data class MainActivityViewModelState(
        val darkTheme: Boolean = false,
        val dynamicColor: Boolean = false
    )

    private val _state: MutableState<MainActivityViewModelState> =
        mutableStateOf(MainActivityViewModelState())
    val state: State<MainActivityViewModelState>
        get() = _state

    init {
        viewModelScope.launch {
            _dataStoreRepository.read(
                key = booleanPreferencesKey(DataStoreConstants.DYNAMIC_COLOR_KEY),
                defaultValue = false
            ).collect { dynamicColor: Boolean ->
                _state.value = _state.value.copy(dynamicColor = dynamicColor)
            }
        }
    }

    fun setTheme(defaultValue: Boolean) {
        viewModelScope.launch {
            _dataStoreRepository.read(
                key = booleanPreferencesKey(name = DataStoreConstants.DARK_THEME_KEY),
                defaultValue = defaultValue
            ).collect { darkTheme: Boolean ->
                _state.value = _state.value.copy(darkTheme = darkTheme)
            }
        }
    }
}
