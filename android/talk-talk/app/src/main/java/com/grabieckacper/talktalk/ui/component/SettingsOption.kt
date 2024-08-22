package com.grabieckacper.talktalk.ui.component

import androidx.compose.material3.ListItem
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.grabieckacper.talktalk.ui.theme.TalkTalkTheme

@Composable
fun SettingsOption(
    name: String,
    modifier: Modifier = Modifier,
    description: @Composable (() -> Unit)? = null,
    content: @Composable (() -> Unit)? = null
) {
    ListItem(
        headlineContent = {
            Text(text = name)
        },
        modifier = modifier,
        supportingContent = description,
        trailingContent = content
    )
}

@Composable
@Preview(showBackground = true)
fun SettingsOptionPreview() {
    TalkTalkTheme {
        SettingsOption(
            name = "Dark theme",
        ) {
            Switch(
                checked = false,
                onCheckedChange = {}
            )
        }
    }
}
