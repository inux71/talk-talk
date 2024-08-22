package com.grabieckacper.talktalk.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.grabieckacper.talktalk.common.TalkType
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TalkListViewModel @Inject constructor() : ViewModel() {
    data class TalkListViewModelState(
        val query: String = "",
        val searchBarActive: Boolean = false,
        val selectedTalkType: TalkType = TalkType.ALL
    )

    private val _state: MutableState<TalkListViewModelState> =
        mutableStateOf(TalkListViewModelState())
    val state: State<TalkListViewModelState>
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

    fun onTalkTypeChange(talkType: TalkType) {
        _state.value = _state.value.copy(selectedTalkType = talkType)
    }
}
