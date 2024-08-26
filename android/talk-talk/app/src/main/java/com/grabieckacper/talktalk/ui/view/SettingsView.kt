package com.grabieckacper.talktalk.ui.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.grabieckacper.talktalk.R
import com.grabieckacper.talktalk.ui.component.SettingsOption
import com.grabieckacper.talktalk.ui.theme.TalkTalkTheme
import com.grabieckacper.talktalk.viewmodel.SettingsViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsView(
    viewModel: SettingsViewModel = hiltViewModel(),
    onNavigateBackToTalkListView: () -> Unit,
    onNavigateToLoginView: () -> Unit
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {
                    Text(text = stringResource(id = R.string.settings_view_title_text))
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
            SettingsOption(name = stringResource(id = R.string.dark_theme_settings_option)) {
                Switch(
                    checked = viewModel.state.value.darkTheme,
                    onCheckedChange = { darkTheme: Boolean ->
                        viewModel.onDarkThemeChange(darkTheme = darkTheme)
                    }
                )
            }

            SettingsOption(name = stringResource(id = R.string.dynamic_color_settings_option)) {
                Switch(
                    checked = viewModel.state.value.dynamicColor,
                    onCheckedChange = { dynamicColor: Boolean ->
                        viewModel.onDynamicColorChange(dynamicColor = dynamicColor)
                    }
                )
            }

            SettingsOption(
                name = stringResource(id = R.string.clear_data_settings_option),
                modifier = Modifier.clickable(onClick = {
                    onNavigateToLoginView()
                    viewModel.clearData()
                })
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun SettingsViewPreview() {
    TalkTalkTheme {
        SettingsView(
            onNavigateBackToTalkListView = {},
            onNavigateToLoginView = {}
        )
    }
}
