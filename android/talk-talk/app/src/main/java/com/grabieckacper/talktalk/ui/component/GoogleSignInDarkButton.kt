package com.grabieckacper.talktalk.ui.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.grabieckacper.talktalk.R
import com.grabieckacper.talktalk.ui.theme.TalkTalkTheme

@Composable
fun GoogleSignInDarkButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    shape: Shape = ButtonDefaults.shape
) {
    Button(
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        shape = shape,
        colors = ButtonColors(
            containerColor = Color(0xFF131314),
            contentColor = Color(0xFFE3E3E3),
            disabledContainerColor = Color(0xFF131314),
            disabledContentColor = Color(0xFF131314)
        ),
        elevation = ButtonDefaults.buttonElevation(),
        border = BorderStroke(
            width = 1.dp,
            color = Color(0xFF8E918F)
        ),
        contentPadding = PaddingValues(
            start = 2.dp,
            end = 12.dp
        ),
    ) {
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.android_dark_rd_na),
                contentDescription = stringResource(
                    id = R.string.android_dark_rd_na_drawable_content_description
                )
            )

            Text(
                text = stringResource(id = R.string.sign_in_with_google_button_text),
                fontSize = 14.sp
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun GoogleSignInDarkButtonPreview() {
    TalkTalkTheme {
        GoogleSignInDarkButton(onClick = {})
    }
}
