package com.grabieckacper.talktalk.ui.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.grabieckacper.talktalk.R
import com.grabieckacper.talktalk.ui.component.GoogleSignInButton
import com.grabieckacper.talktalk.ui.theme.TalkTalkTheme
import com.grabieckacper.talktalk.viewmodel.LoginViewModel

@Composable
fun LoginView(
    viewModel: LoginViewModel = hiltViewModel()
) {
    Scaffold(modifier = Modifier.fillMaxSize()) { paddingValues: PaddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues = paddingValues),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = stringResource(id = R.string.app_name))

            OutlinedTextField(
                value = viewModel.state.value.email,
                onValueChange = { email: String ->
                    viewModel.onEmailChange(email = email)
                    viewModel.validateEmail()
                },
                modifier = Modifier.fillMaxWidth(0.7f),
                label = {
                    Text(text = stringResource(id = R.string.email_label))
                },
                placeholder = {
                    Text(text = stringResource(id = R.string.email_placeholder))
                },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Email,
                        contentDescription = stringResource(
                            id = R.string.email_icon_content_description
                        )
                    )
                },
                trailingIcon = {
                    if (viewModel.state.value.email.isNotBlank()) {
                        IconButton(onClick = {
                            viewModel.clearEmail()
                        }) {
                            Icon(
                                imageVector = Icons.Default.Clear,
                                contentDescription = stringResource(
                                    id = R.string.clear_icon_content_description
                                )
                            )
                        }
                    }
                },
                supportingText = {
                    Text(
                        text = viewModel.state.value.emailSupportingText.asString(),
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 1
                    )
                },
                isError = viewModel.state.value.emailError,
                keyboardOptions = KeyboardOptions(
                    autoCorrect = false,
                    keyboardType = KeyboardType.Email,
                    imeAction = ImeAction.Next
                ),
                singleLine = true
            )

            OutlinedTextField(
                value = viewModel.state.value.password,
                onValueChange = { password: String ->
                    viewModel.onPasswordChange(password = password)
                    viewModel.validatePassword()
                },
                modifier = Modifier.fillMaxWidth(0.7f),
                label = {
                    Text(text = stringResource(id = R.string.password_label))
                },
                placeholder = {
                    Text(text = stringResource(id = R.string.password_placeholder))
                },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Lock,
                        contentDescription = stringResource(
                            id = R.string.lock_icon_content_description
                        )
                    )
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
                singleLine = true
            )

            Button(
                onClick = { /*TODO*/ },
                modifier = Modifier.fillMaxWidth(0.7f)
            ) {
                Text(text = stringResource(id = R.string.sign_in_button_text))
            }

            Row(
                modifier = Modifier.fillMaxWidth(0.7f),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                HorizontalDivider(modifier = Modifier.weight(1.0f))

                Text(
                    text = stringResource(id = R.string.or_divider_text),
                    modifier = Modifier.padding(horizontal = 4.dp)
                )
                
                HorizontalDivider(modifier = Modifier.weight(1.0f))
            }

            GoogleSignInButton(
                onClick = { /*TODO*/ },
                modifier = Modifier.fillMaxWidth(0.7f)
            )

            Row(
                modifier = Modifier.fillMaxWidth(0.7f),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = stringResource(id = R.string.no_account_text))

                TextButton(onClick = { /*TODO*/ }) {
                    Text(text = stringResource(id = R.string.sign_up_button_text))
                }
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun LoginViewPreview() {
    TalkTalkTheme {
        LoginView()
    }
}
