package com.grabieckacper.talktalk.ui.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.grabieckacper.talktalk.R
import com.grabieckacper.talktalk.ui.component.ProfileLabel
import com.grabieckacper.talktalk.ui.theme.TalkTalkTheme
import com.grabieckacper.talktalk.viewmodel.NewTalkViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewTalkView(
    viewModel: NewTalkViewModel = hiltViewModel(),
    onNavigateBackToTalkListView: () -> Unit,
    onNavigateToTalkView: (Long) -> Unit,
    onNavigateToNewGroupTalkView: () -> Unit
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {
                    Text(text = stringResource(id = R.string.new_talk_view_title_text))
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
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                onNavigateToNewGroupTalkView()
            }) {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_groups_24),
                    contentDescription = stringResource(
                        id = R.string.groups_drawable_content_description
                    )
                )
            }
        }
    ) { paddingValues: PaddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues = paddingValues),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            SearchBar(
                query = viewModel.state.value.query,
                onQueryChange = { query: String ->
                    viewModel.onQueryChange(query = query)
                },
                onSearch = {

                },
                active = viewModel.state.value.searchBarActive,
                onActiveChange = {
                    viewModel.onSearchBarActiveChange()
                },
                placeholder = {
                    Text(text = stringResource(id = R.string.searchbar_placeholder))
                },
                leadingIcon = {
                    if (viewModel.state.value.searchBarActive) {
                        IconButton(onClick = {
                            viewModel.onSearchBarActiveChange()
                        }) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Default.ArrowBack,
                                contentDescription = stringResource(
                                    id = R.string.arrow_back_icon_content_description
                                )
                            )
                        }
                    } else {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = stringResource(
                                id = R.string.search_icon_content_description
                            )
                        )
                    }
                },
                trailingIcon = {
                    if (viewModel.state.value.query.isNotBlank()) {
                        IconButton(onClick = {
                            viewModel.clearQuery()
                        }) {
                            Icon(
                                imageVector = Icons.Default.Clear,
                                contentDescription = stringResource(
                                    id = R.string.clear_icon_content_description
                                )
                            )
                        }
                    }
                }
            ) {
                ProfileLabel(
                    onClick = { /*TODO*/ },
                    profileName = "Kacper Grabiec",
                    profileImageUrl = "https://scontent-waw2-2.xx.fbcdn.net/v/t39.30808-1/406051853_3340379186261131_8985740721870838088_n.jpg?stp=cp6_dst-jpg_s480x480&_nc_cat=109&ccb=1-7&_nc_sid=0ecb9b&_nc_ohc=v97YlMmqj64Q7kNvgFG1f1S&_nc_ht=scontent-waw2-2.xx&oh=00_AYCWlbfSDXKM7rpbk4DPKpD6ncVaWqbxgnetuoShozKJ5w&oe=66CCDFBA"
                )

                ProfileLabel(
                    onClick = { /*TODO*/ },
                    profileName = "Kacper Grabiec",
                    profileImageUrl = "https://scontent-waw2-2.xx.fbcdn.net/v/t39.30808-1/406051853_3340379186261131_8985740721870838088_n.jpg?stp=cp6_dst-jpg_s480x480&_nc_cat=109&ccb=1-7&_nc_sid=0ecb9b&_nc_ohc=v97YlMmqj64Q7kNvgFG1f1S&_nc_ht=scontent-waw2-2.xx&oh=00_AYCWlbfSDXKM7rpbk4DPKpD6ncVaWqbxgnetuoShozKJ5w&oe=66CCDFBA"
                )
            }

            LazyColumn(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                items(count = 10) {
                    ProfileLabel(
                        onClick = {
                            onNavigateToTalkView(1L)
                        },
                        profileName = "Kacper Grabiec",
                        profileImageUrl = "https://scontent-waw2-2.xx.fbcdn.net/v/t39.30808-1/406051853_3340379186261131_8985740721870838088_n.jpg?stp=cp6_dst-jpg_s480x480&_nc_cat=109&ccb=1-7&_nc_sid=0ecb9b&_nc_ohc=v97YlMmqj64Q7kNvgFG1f1S&_nc_ht=scontent-waw2-2.xx&oh=00_AYCWlbfSDXKM7rpbk4DPKpD6ncVaWqbxgnetuoShozKJ5w&oe=66CCDFBA"
                    )
                }
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun NewTalkViewPreview() {
    TalkTalkTheme {
        NewTalkView(
            onNavigateBackToTalkListView = {},
            onNavigateToTalkView = { _ -> },
            onNavigateToNewGroupTalkView = {}
        )
    }
}
