package com.grabieckacper.talktalk.viewmodel

import android.util.Log
import android.util.Patterns
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.grabieckacper.talktalk.R
import com.grabieckacper.talktalk.common.StringValue
import com.grabieckacper.talktalk.request.CreateUserRequest
import com.grabieckacper.talktalk.service.UserService
import dagger.hilt.android.lifecycle.HiltViewModel
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.RedirectResponseException
import io.ktor.client.plugins.ServerResponseException
import io.ktor.client.statement.bodyAsText
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(private val _userService: UserService) : ViewModel() {
    data class RegisterViewModelState(
        val isLoading: Boolean = false,
        val isError: Boolean = false,
        val errorMessage: StringValue = StringValue.Empty,
        // First name
        val firstName: String = "",
        val firstNameSupportingText: StringValue = StringValue.Empty,
        val firstNameError: Boolean = false,
        // Last name
        val lastName: String = "",
        val lastNameSupportingText: StringValue = StringValue.Empty,
        val lastNameError: Boolean = false,
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
        _state.value = _state.value.copy(firstName = firstName.trim())
    }

    fun validateFirstName() {
        if (_state.value.firstName.isBlank()) {
            _state.value = _state.value.copy(
                firstNameSupportingText = StringValue.StringResource(
                    resId = R.string.invalid_first_name_text
                ),
                firstNameError = true
            )
        } else {
            _state.value = _state.value.copy(
                firstNameSupportingText = StringValue.Empty,
                firstNameError = false
            )
        }
    }

    fun clearFirstName() {
        _state.value = _state.value.copy(firstName = "")
    }

    fun onLastNameChange(lastName: String) {
        _state.value = _state.value.copy(lastName = lastName.trim())
    }

    fun validateLastName() {
        if (_state.value.lastName.isBlank()) {
            _state.value = _state.value.copy(
                lastNameSupportingText = StringValue.StringResource(
                    resId = R.string.invalid_last_name_text
                ),
                lastNameError = true
            )
        } else {
            _state.value = _state.value.copy(
                lastNameSupportingText = StringValue.Empty,
                lastNameError = false
            )
        }
    }

    fun clearLastName() {
        _state.value = _state.value.copy(lastName = "")
    }

    fun onEmailChange(email: String) {
        _state.value = _state.value.copy(email = email.trim())
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
        _state.value = _state.value.copy(password = password.trim())
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

    fun onSignUpButtonClick(onSignedUp: () -> Unit) {
        viewModelScope.launch {
            validateFirstName()
            validateLastName()
            validateEmail()
            validatePassword()

            if (_state.value.firstNameError ||
                _state.value.lastNameError ||
                _state.value.emailError ||
                _state.value.passwordError) {
                return@launch
            }

            try {
                _state.value = _state.value.copy(isLoading = true)

                val createUserRequest = CreateUserRequest(
                    email = _state.value.email,
                    password = _state.value.password,
                    firstName = _state.value.firstName,
                    lastName = _state.value.lastName
                )

                _userService.register(createUserRequest = createUserRequest)

                onSignedUp()
            } catch (e: RedirectResponseException) {
                _state.value = _state.value.copy(
                    isError = true,
                    errorMessage = StringValue.DynamicString(value = e.response.bodyAsText())
                )

                Log.e("[3xx]", e.message)
            } catch (e: ClientRequestException) {
                _state.value = _state.value.copy(
                    isError = true,
                    errorMessage = StringValue.DynamicString(value = e.response.bodyAsText())
                )

                Log.e("[4xx]", e.message)
            } catch (e: ServerResponseException) {
                _state.value = _state.value.copy(
                    isError = true,
                    errorMessage = StringValue.DynamicString(value = e.response.bodyAsText())
                )

                Log.e("[5xx]", e.message)
            } catch (e: Exception) {
                _state.value = _state.value.copy(
                    isError = true,
                    errorMessage = StringValue.DynamicString(value = e.message ?: "")
                )

                Log.e("[?]", e.message ?: "")
            } finally {
                _state.value = _state.value.copy(isLoading = false)
            }
        }
    }

    fun clearError() {
        _state.value = _state.value.copy(
            isError = false,
            errorMessage = StringValue.Empty
        )
    }
}
