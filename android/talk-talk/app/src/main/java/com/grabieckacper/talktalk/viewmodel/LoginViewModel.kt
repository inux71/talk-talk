package com.grabieckacper.talktalk.viewmodel

import android.util.Log
import android.util.Patterns
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.grabieckacper.talktalk.R
import com.grabieckacper.talktalk.common.DataStoreConstants
import com.grabieckacper.talktalk.common.StringValue
import com.grabieckacper.talktalk.repository.DataStoreRepository
import com.grabieckacper.talktalk.request.LoginRequest
import com.grabieckacper.talktalk.response.LoginResponse
import com.grabieckacper.talktalk.service.AuthenticationService
import dagger.hilt.android.lifecycle.HiltViewModel
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.RedirectResponseException
import io.ktor.client.plugins.ServerResponseException
import io.ktor.client.statement.bodyAsText
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val _dataStoreRepository: DataStoreRepository,
    private val _authenticationService: AuthenticationService
) : ViewModel() {
    data class LoginViewModelState(
        val isLoading: Boolean = false,
        val isError: Boolean = false,
        val errorMessage: StringValue = StringValue.Empty,
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

    private val _state: MutableState<LoginViewModelState> = mutableStateOf(LoginViewModelState())
    val state: State<LoginViewModelState>
        get() = _state

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

    fun onSignInButtonClick(onSignedIn: () -> Unit) {
        viewModelScope.launch {
            validateEmail()
            validatePassword()

            if (_state.value.emailError || _state.value.passwordError) {
                return@launch
            }

            try {
                _state.value = _state.value.copy(isLoading = true)

                val loginRequest = LoginRequest(
                    email = _state.value.email,
                    password = _state.value.password
                )

                val loginResponse: LoginResponse = _authenticationService.login(
                    loginRequest = loginRequest
                )

                _dataStoreRepository.write(
                    key = stringPreferencesKey(name = DataStoreConstants.ACCESS_TOKEN_KEY),
                    value = loginResponse.token
                )

                Log.i("[TOKEN]", loginResponse.token)

                onSignedIn()
            } catch (e: RedirectResponseException) {
                _state.value = _state.value.copy(
                    isError = true,
                    errorMessage = StringValue.DynamicString(value = e.response.bodyAsText())
                )
            } catch (e: ClientRequestException) {
                _state.value = _state.value.copy(
                    isError = true,
                    errorMessage = StringValue.DynamicString(value = e.response.bodyAsText())
                )
            } catch (e: ServerResponseException) {
                _state.value = _state.value.copy(
                    isError = true,
                    errorMessage = StringValue.DynamicString(value = e.response.bodyAsText())
                )
            } catch (e: Exception) {
                _state.value = _state.value.copy(
                    isError = true,
                    errorMessage = StringValue.DynamicString(value = e.message ?: "")
                )
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
