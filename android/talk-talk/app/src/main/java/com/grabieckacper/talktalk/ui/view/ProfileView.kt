package com.grabieckacper.talktalk.ui.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.grabieckacper.talktalk.R
import com.grabieckacper.talktalk.ui.component.SettingsOption
import com.grabieckacper.talktalk.ui.theme.TalkTalkTheme
import com.grabieckacper.talktalk.viewmodel.ProfileViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileView(
    viewModel: ProfileViewModel = hiltViewModel(),
    onNavigateBackToTalkListView: () -> Unit,
    onNavigateToLoginView: () -> Unit
) {
    val focusManager: FocusManager = LocalFocusManager.current

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Kacper Grabiec")
                },
                navigationIcon = {
                    IconButton(onClick = {
                        onNavigateBackToTalkListView()
                    }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Default.ArrowBack,
                            contentDescription = stringResource(
                                id = R.string.arrow_back_icon_content_description
                            )
                        )
                    }
                }
            )
        }
    ) { paddingValues: PaddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues = paddingValues),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AsyncImage(
                model = viewModel.state.value.profileImageUrl,
                contentDescription = stringResource(
                    id = R.string.profile_picture_content_description
                ),
                modifier = Modifier
                    .size(size = 128.dp)
                    .clip(shape = CircleShape)
            )

            TextButton(onClick = { /*TODO*/ }) {
                Text(text = stringResource(id = R.string.change_profile_picture_button_text))
            }

            SettingsOption(name = stringResource(id = R.string.change_password_settings_option)) {
                TextField(
                    value = viewModel.state.value.password,
                    onValueChange = { password: String ->
                        viewModel.onPasswordChange(password = password)
                        viewModel.validatePassword()
                    },
                    modifier = Modifier.fillMaxWidth(0.6f),
                    placeholder = {
                        Text(text = stringResource(id = R.string.password_placeholder))
                    },
                    trailingIcon = {
                        IconButton(onClick = {
                            viewModel.onPasswordVisibilityChange()
                        }) {
                            if (viewModel.state.value.passwordVisible) {
                                Icon(
                                    painter = painterResource(
                                        id = R.drawable.baseline_visibility_off_24
                                    ),
                                    contentDescription = stringResource(
                                        id = R.string.visibility_off_drawable_content_description
                                    )
                                )
                            } else {
                                Icon(
                                    painter = painterResource(
                                        id = R.drawable.baseline_visibility_24
                                    ),
                                    contentDescription = stringResource(
                                        id = R.string.visibility_drawable_content_description
                                    )
                                )
                            }
                        }
                    },
                    supportingText = {
                        Text(text = viewModel.state.value.passwordSupportingText.asString())
                    },
                    isError = viewModel.state.value.passwordError,
                    visualTransformation = if (viewModel.state.value.passwordVisible) {
                        VisualTransformation.None
                    } else {
                        PasswordVisualTransformation()
                    },
                    keyboardOptions = KeyboardOptions(
                        autoCorrect = false,
                        keyboardType = KeyboardType.Password,
                        imeAction = ImeAction.Done
                    ),
                    keyboardActions = KeyboardActions(onDone = {
                        // ToDo
                        focusManager.clearFocus()
                    }),
                    singleLine = true
                )
            }

            SettingsOption(
                name = stringResource(id = R.string.sign_out_settings_option),
                modifier = Modifier.clickable(onClick = {
                    onNavigateToLoginView()
                })
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Default.ExitToApp,
                    contentDescription = stringResource(
                        id = R.string.exit_to_app_icon_content_description
                    )
                )
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun ProfileViewPreview() {
    TalkTalkTheme {
        ProfileView(
            onNavigateBackToTalkListView = {},
            onNavigateToLoginView = {}
        )
    }
}
