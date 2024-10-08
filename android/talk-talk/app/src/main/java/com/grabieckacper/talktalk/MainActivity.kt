package com.grabieckacper.talktalk

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.grabieckacper.talktalk.ui.theme.TalkTalkTheme
import com.grabieckacper.talktalk.viewmodel.MainActivityViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val _viewModel: MainActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContent {
            val navHostController: NavHostController = rememberNavController()

            _viewModel.setTheme(defaultValue = isSystemInDarkTheme())

            TalkTalkTheme(
                darkTheme = _viewModel.state.value.darkTheme,
                dynamicColor = _viewModel.state.value.dynamicColor
            ) {
                NavigationController(
                    navHostController = navHostController,
                    startDestination = if (_viewModel.state.value.accessToken.isNotEmpty()) {
                        Route.TALKS
                    } else {
                        Route.AUTH
                    }
                )
            }
        }
    }
}
