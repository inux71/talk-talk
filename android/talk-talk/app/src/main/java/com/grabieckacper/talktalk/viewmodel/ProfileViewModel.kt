package com.grabieckacper.talktalk.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.grabieckacper.talktalk.R
import com.grabieckacper.talktalk.common.StringValue
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor() : ViewModel() {
    data class ProfileViewModelState(
        val profileImageUrl: String = "https://scontent-waw2-2.xx.fbcdn.net/v/t39.30808-1/406051853_3340379186261131_8985740721870838088_n.jpg?stp=cp6_dst-jpg_s480x480&_nc_cat=109&ccb=1-7&_nc_sid=0ecb9b&_nc_ohc=v97YlMmqj64Q7kNvgFG1f1S&_nc_ht=scontent-waw2-2.xx&oh=00_AYCWlbfSDXKM7rpbk4DPKpD6ncVaWqbxgnetuoShozKJ5w&oe=66CCDFBA",
        // Password
        val password: String = "",
        val passwordVisible: Boolean = false,
        val passwordSupportingText: StringValue = StringValue.Empty,
        val passwordError: Boolean = false
    )

    private val _state: MutableState<ProfileViewModelState> = mutableStateOf(ProfileViewModelState())
    val state: State<ProfileViewModelState>
        get() = _state

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
