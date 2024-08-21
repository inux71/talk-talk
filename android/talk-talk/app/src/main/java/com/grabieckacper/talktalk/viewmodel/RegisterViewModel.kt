package com.grabieckacper.talktalk.viewmodel

import android.util.Patterns
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.grabieckacper.talktalk.R
import com.grabieckacper.talktalk.common.StringValue
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor() : ViewModel() {
    data class RegisterViewModelState(
        // First name
        val firstName: String = "",
        // Last name
        val lastName: String = "",
        // Email
        val email: String = "",
        val emailSupportingText: StringValue = StringValue.Empty,
        val emailError: Boolean = false,
        // Password
        val password: String = "",
        val passwordVisible: Boolean = false,
        val passwordSupportingText: StringValue = StringValue.Empty,
        val passwordError: Boolean = false
    )

    private val _state: MutableState<RegisterViewModelState> =
        mutableStateOf(RegisterViewModelState())
    val state: State<RegisterViewModelState>
        get() = _state

    fun onFirstNameChange(firstName: String) {
        _state.value = _state.value.copy(firstName = firstName)
    }

    fun clearFirstName() {
        _state.value = _state.value.copy(firstName = "")
    }

    fun onLastNameChange(lastName: String) {
        _state.value = _state.value.copy(lastName = lastName)
    }

    fun clearLastName() {
        _state.value = _state.value.copy(lastName = "")
    }

    fun onEmailChange(email: String) {
        _state.value = _state.value.copy(email = email)
    }

    fun validateEmail() {
        if (!Patterns.EMAIL_ADDRESS.matcher(_state.value.email).matches()) {
            _state.value = _state.value.copy(
                emailSupportingText = StringValue.StringResource(resId = R.string.invalid_email_text),
                emailError = true
            )
        } else {
            _state.value = _state.value.copy(
                emailSupportingText = StringValue.Empty,
                emailError = false
            )
        }
    }

    fun clearEmail() {
        _state.value = _state.value.copy(email = "")
    }

    fun onPasswordChange(password: String) {
        _state.value = _state.value.copy(password = password)
    }

    fun validatePassword() {
        if (_state.value.password.isBlank() || _state.value.password.length < 8) {
            _state.value = _state.value.copy(
                passwordSupportingText = StringValue.StringResource(
                    resId = R.string.invalid_password_text
                ),
                passwordError = true
            )
        } else {
            _state.value = _state.value.copy(
                passwordSupportingText = StringValue.Empty,
                passwordError = false
            )
        }
    }

    fun onPasswordVisibilityChange() {
        _state.value = _state.value.copy(passwordVisible = !_state.value.passwordVisible)
    }
}
