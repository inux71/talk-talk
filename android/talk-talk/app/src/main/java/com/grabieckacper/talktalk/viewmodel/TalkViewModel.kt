package com.grabieckacper.talktalk.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TalkViewModel @Inject constructor() : ViewModel() {
    data class TalkViewModelState(
        val talkDropDownMenuExpanded: Boolean = false,
        val messageDropDownMenuExpanded: Boolean = false,
        val message: String = ""
    )

    private val _state: MutableState<TalkViewModelState> = mutableStateOf(TalkViewModelState())
    val state: State<TalkViewModelState>
        get() = _state

    fun onTalkDropDownMenuExpandedChange() {
        _state.value = _state.value.copy(
            talkDropDownMenuExpanded = !_state.value.talkDropDownMenuExpanded
        )
    }

    fun closeTalkDropDownMenu() {
        _state.value = _state.value.copy(talkDropDownMenuExpanded = false)
    }

    fun onMessageDropDownMenuExpandedChange() {
        _state.value = _state.value.copy(
            messageDropDownMenuExpanded = !_state.value.messageDropDownMenuExpanded
        )
    }

    fun closeMessageDropDownMenu() {
        _state.value = _state.value.copy(messageDropDownMenuExpanded = false)
    }

    fun onMessageChange(message: String) {
        _state.value = _state.value.copy(message = message)
    }
}
