package com.grabieckacper.talktalk.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Badge
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.grabieckacper.talktalk.R
import com.grabieckacper.talktalk.extensions.messageDate
import com.grabieckacper.talktalk.ui.theme.TalkTalkTheme
import java.util.Calendar
import java.util.Date

@Composable
fun TalkHeader(
    onClick: () -> Unit,
    talkName: String,
    lastMessageSenderName: String,
    lastMessage: String,
    lastMessageDate: Date,
    muted: Boolean,
    blocked: Boolean,
    unread: Boolean,
    unreadMessages: Int? = null,
    talkImageUrl: String
) {
    ListItem(
        headlineContent = { 
            Text(
                text = talkName,
                fontWeight = if (unread) {
                    FontWeight.Bold
                } else null,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1
            )
        },
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        supportingContent = {
            Text(
                text = "$lastMessageSenderName: $lastMessage",
                fontWeight = if (unread) {
                    FontWeight.Bold
                } else null,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1
            )
        },
        leadingContent = {
            AsyncImage(
                model = talkImageUrl,
                contentDescription = stringResource(id = R.string.talk_picture_content_description),
                modifier = Modifier
                    .size(size = 48.dp)
                    .clip(shape = CircleShape)
            )
        },
        trailingContent = {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.End
            ) {
                val messageCalendar: Calendar = Calendar.getInstance().apply {
                    time = lastMessageDate
                }

                Text(
                    text = messageCalendar.messageDate(),
                    fontWeight = if (unread) {
                        FontWeight.Bold
                    } else null,
                )
                Row(
                    horizontalArrangement = Arrangement.End,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    if (muted) {
                        Icon(
                            painter = painterResource(id = R.drawable.baseline_notifications_off_24),
                            contentDescription = stringResource(
                                id = R.string.notifications_off_drawable_content_description
                            )
                        )
                    }

                    if (blocked) {
                        Icon(
                            painter = painterResource(id = R.drawable.baseline_block_24),
                            contentDescription = stringResource(
                                id = R.string.block_drawable_content_description
                            )
                        )
                    }

                    if (unread) {
                        Badge {
                            if (unreadMessages != null) {
                                Text(
                                    text = "$unreadMessages",
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }
                    }
                }
            }
        }
    )
}

@Composable
@Preview(showBackground = true)
fun TalkHeaderPreview() {
    TalkTalkTheme {
        TalkHeader(
            talkName = "Kacper Grabiec",
            onClick = {},
            lastMessageSenderName = "You",
            lastMessage = "Ok",
            lastMessageDate = Date(),
            muted = false,
            blocked = false,
            unread = false,
            talkImageUrl = "https://scontent-waw2-2.xx.fbcdn.net/v/t39.30808-1/406051853_3340379186261131_8985740721870838088_n.jpg?stp=cp6_dst-jpg_s480x480&_nc_cat=109&ccb=1-7&_nc_sid=0ecb9b&_nc_ohc=v97YlMmqj64Q7kNvgFG1f1S&_nc_ht=scontent-waw2-2.xx&oh=00_AYCWlbfSDXKM7rpbk4DPKpD6ncVaWqbxgnetuoShozKJ5w&oe=66CCDFBA"
        )
    }
}
