package com.grabieckacper.talktalk.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.grabieckacper.talktalk.extensions.messageDate
import com.grabieckacper.talktalk.ui.theme.TalkTalkTheme
import java.util.Calendar
import java.util.Date

@Composable
fun TextMessage(
    message: String,
    date: Date,
    reversed: Boolean = false,
) {
    Row(
        modifier = Modifier.fillMaxWidth(0.9f),
        horizontalArrangement = if (reversed) {
            Arrangement.End
        } else {
            Arrangement.Start
        },
        verticalAlignment = Alignment.CenterVertically
    ) {
        val messageCalendar: Calendar = Calendar.getInstance().apply {
            time = date
        }

        Text(
            text = "$message ${messageCalendar.messageDate()}",
            modifier = Modifier.padding(all = 1.dp)
                .background(
                    color = if (reversed) {
                        MaterialTheme.colorScheme.primary
                    } else {
                        MaterialTheme.colorScheme.secondary
                    },
                    shape = RoundedCornerShape(percent = 25)
                ).padding(all = 5.dp),
            color = Color.White
        )
    }
}

@Composable
@Preview(showBackground = true)
fun TextMessagePreview() {
    TalkTalkTheme {
        TextMessage(
            message = "Good morning!",
            date = Date(),
            reversed = true
        )
    }
}
