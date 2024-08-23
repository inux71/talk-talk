package com.grabieckacper.talktalk.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.grabieckacper.talktalk.R
import com.grabieckacper.talktalk.ui.theme.TalkTalkTheme

@Composable
fun ProfileLabel(
    onClick: () -> Unit,
    profileName: String,
    profileImageUrl: String
) {
    ListItem(
        headlineContent = {
            Text(text = profileName)
        },
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        leadingContent = {
            AsyncImage(
                model = profileImageUrl,
                contentDescription = stringResource(id = R.string.talk_picture_content_description),
                modifier = Modifier
                    .size(size = 48.dp)
                    .clip(shape = CircleShape)
            )
        }
    )
}

@Composable
@Preview(showBackground = true)
fun ProfileLabelPreview() {
    TalkTalkTheme {
        ProfileLabel(
            onClick = {},
            profileName = "Kacper Grabiec",
            profileImageUrl = "https://scontent-waw2-2.xx.fbcdn.net/v/t39.30808-1/406051853_3340379186261131_8985740721870838088_n.jpg?stp=cp6_dst-jpg_s480x480&_nc_cat=109&ccb=1-7&_nc_sid=0ecb9b&_nc_ohc=v97YlMmqj64Q7kNvgFG1f1S&_nc_ht=scontent-waw2-2.xx&oh=00_AYCWlbfSDXKM7rpbk4DPKpD6ncVaWqbxgnetuoShozKJ5w&oe=66CCDFBA"
        )
    }
}
