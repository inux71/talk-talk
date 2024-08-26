package com.grabieckacper.talktalk.ui.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.grabieckacper.talktalk.R
import com.grabieckacper.talktalk.ui.component.TextMessage
import com.grabieckacper.talktalk.ui.theme.TalkTalkTheme
import com.grabieckacper.talktalk.viewmodel.TalkViewModel
import java.util.Date

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TalkView(
    viewModel: TalkViewModel = hiltViewModel()
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Kacper Grabiec",
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 1
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Default.ArrowBack,
                            contentDescription = stringResource(
                                id = R.string.arrow_back_icon_content_description
                            )
                        )
                    }
                },
                actions = {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(
                            imageVector = Icons.Default.Call,
                            contentDescription = stringResource(
                                id = R.string.call_icon_content_description
                            )
                        )
                    }

                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(
                            painter = painterResource(id = R.drawable.baseline_videocam_24),
                            contentDescription = stringResource(
                                id = R.string.videocam_drawable_content_description
                            )
                        )
                    }

                    IconButton(onClick = {
                        viewModel.onTalkDropDownMenuExpandedChange()
                    }) {
                        Icon(
                            imageVector = Icons.Default.MoreVert,
                            contentDescription = stringResource(
                                id = R.string.more_vert_icon_content_description
                            )
                        )
                    }

                    DropdownMenu(
                        expanded = viewModel.state.value.talkDropDownMenuExpanded,
                        onDismissRequest = {
                            viewModel.closeTalkDropDownMenu()
                        }
                    ) {
                        DropdownMenuItem(
                            text = { 
                                Text(text = stringResource(id = R.string.mute_text))
                            },
                            onClick = { /*TODO*/ },
                            leadingIcon = {
                                Icon(
                                    painter = painterResource(
                                        id = R.drawable.baseline_notifications_off_24
                                    ),
                                    contentDescription = stringResource(
                                        id = R.string.notifications_off_drawable_content_description
                                    )
                                )
                            }
                        )

                        DropdownMenuItem(
                            text = {
                                Text(text = stringResource(id = R.string.block_text))
                            },
                            onClick = { /*TODO*/ },
                            leadingIcon = {
                                Icon(
                                    painter = painterResource(id = R.drawable.baseline_block_24),
                                    contentDescription = stringResource(
                                        id = R.string.block_drawable_content_description
                                    )
                                )
                            }
                        )
                    }
                }
            )
        },
        bottomBar = {
            BottomAppBar(
                modifier = Modifier.fillMaxWidth()
            ) {
                DropdownMenu(
                    expanded = viewModel.state.value.messageDropDownMenuExpanded,
                    onDismissRequest = {
                        viewModel.closeMessageDropDownMenu()
                    }
                ) {
                    DropdownMenuItem(
                        text = {
                            Text(text = stringResource(id = R.string.attachment_text))
                        },
                        onClick = { /*TODO*/ },
                        leadingIcon = {
                            Icon(
                                painter = painterResource(
                                    id = R.drawable.baseline_attach_file_24
                                ),
                                contentDescription = stringResource(
                                    id = R.string.attach_file_drawable_content_description
                                )
                            )
                        }
                    )

                    DropdownMenuItem(
                        text = {
                            Text(text = stringResource(id = R.string.location_text))
                        },
                        onClick = { /*TODO*/ },
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Default.LocationOn,
                                contentDescription = stringResource(
                                    id = R.string.location_on_icon_content_description
                                )
                            )
                        }
                    )
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(onClick = {
                        viewModel.onMessageDropDownMenuExpandedChange()
                    }) {
                        Icon(
                            imageVector = Icons.Default.Add,
                            contentDescription = stringResource(
                                id = R.string.add_icon_content_description
                            )
                        )
                    }

                    OutlinedTextField(
                        value = viewModel.state.value.message,
                        onValueChange = { message: String ->
                            viewModel.onMessageChange(message = message)
                        },
                        modifier = Modifier.fillMaxWidth(0.8f),
                        placeholder = {
                            Text(text = stringResource(id = R.string.message_placeholder))
                        },
                        leadingIcon = {
                            IconButton(onClick = { /*TODO*/ }) {
                                Icon(
                                    painter = painterResource(id = R.drawable.baseline_image_24),
                                    contentDescription = stringResource(
                                        id = R.string.image_drawable_content_description
                                    )
                                )
                            }
                        },
                        trailingIcon = {
                            IconButton(
                                onClick = { /*TODO*/ },
                                enabled = viewModel.state.value.message.isNotBlank()
                            ) {
                                Icon(
                                    imageVector = Icons.AutoMirrored.Default.Send,
                                    contentDescription = stringResource(
                                        id = R.string.send_icon_content_description
                                    )
                                )
                            }
                        },
                        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done)
                    )

                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(
                            painter = painterResource(id = R.drawable.baseline_photo_camera_24),
                            contentDescription = stringResource(
                                id = R.string.photo_camera_drawable_content_description
                            )
                        )
                    }
                }
            }
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { /*TODO*/ }) {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_mic_24),
                    contentDescription = stringResource(
                        id = R.string.mic_drawable_content_description
                    )
                )
            }
        }
    ) { paddingValues: PaddingValues ->
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = paddingValues,
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                TextMessage(
                    message = "Hi!",
                    date = Date()
                )
            }

            item {
                TextMessage(
                    message = "What's up?",
                    date = Date()
                )
            }

            item {
                TextMessage(
                    message = "Hello!",
                    date = Date(),
                    reversed = true
                )
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun TalkViewPreview() {
    TalkTalkTheme {
        TalkView()
    }
}
