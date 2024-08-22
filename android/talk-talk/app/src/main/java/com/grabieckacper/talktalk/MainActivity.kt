package com.grabieckacper.talktalk

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.grabieckacper.talktalk.ui.theme.TalkTalkTheme
import com.grabieckacper.talktalk.ui.view.TalkListView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContent {
            TalkTalkTheme {
                TalkListView()
            }
        }
    }
}
