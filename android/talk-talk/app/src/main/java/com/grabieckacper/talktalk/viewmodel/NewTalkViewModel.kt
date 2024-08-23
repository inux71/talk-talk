package com.grabieckacper.talktalk.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NewTalkViewModel @Inject constructor() : ViewModel() {
    data class NewTalkViewModelState(
        val query: String = "",
        val searchBarActive: Boolean = false
    )

    private val _state: MutableState<NewTalkViewModelState> = mutableStateOf(NewTalkViewModelState())
    val state: State<NewTalkViewModelState>
        get() = _state

    fun onQueryChange(query: String) {
        _state.value = _state.value.copy(query = query)
    }

    fun clearQuery() {
        _state.value = _state.value.copy(query = "")
    }

    fun onSearchBarActiveChange() {
        _state.value = _state.value.copy(searchBarActive = !_state.value.searchBarActive)
    }
}
