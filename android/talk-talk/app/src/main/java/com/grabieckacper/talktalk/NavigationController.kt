package com.grabieckacper.talktalk

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.navArgument
import com.grabieckacper.talktalk.ui.view.LoginView
import com.grabieckacper.talktalk.ui.view.NewGroupTalkView
import com.grabieckacper.talktalk.ui.view.NewTalkView
import com.grabieckacper.talktalk.ui.view.ProfileView
import com.grabieckacper.talktalk.ui.view.RegisterView
import com.grabieckacper.talktalk.ui.view.SettingsView
import com.grabieckacper.talktalk.ui.view.TalkListView
import com.grabieckacper.talktalk.ui.view.TalkView

@Composable
fun NavigationController(
    navHostController: NavHostController,
    startDestination: String
) {
    NavHost(
        navController = navHostController,
        startDestination = startDestination
    ) {
        navigation(
            startDestination = Route.LOGIN,
            route = Route.AUTH
        ) {
            composable(route = Route.LOGIN) {
                LoginView(
                    onNavigateToTalkListView = {
                        navHostController.navigate(route = Route.TALK_LIST) {
                            popUpTo(route = Route.AUTH) {
                                inclusive = true
                            }
                        }
                    },
                    onNavigateToRegisterView = {
                        navHostController.navigate(route = Route.REGISTER)
                    }
                )
            }

            composable(route = Route.REGISTER) {
                RegisterView(
                    onNavigateBackToLoginView = {
                        navHostController.popBackStack(
                            route = Route.LOGIN,
                            inclusive = false
                        )
                    },
                    onNavigateToTalkListView = {
                        navHostController.navigate(route = Route.TALK_LIST) {
                            popUpTo(route = Route.AUTH) {
                                inclusive = true
                            }
                        }
                    }
                )
            }
        }

        navigation(
            startDestination = Route.TALK_LIST,
            route = Route.TALKS
        ) {
            composable(route = Route.TALK_LIST) {
                TalkListView(
                    onNavigateToProfileView = {
                        navHostController.navigate(route = Route.PROFILE)
                    },
                    onNavigateToSettingsView = {
                        navHostController.navigate(route = Route.SETTINGS)
                    },
                    onNavigateToTalkView = { talkId: Long ->
                        navHostController.navigate(route = "${Route.TALK}/$talkId")
                    },
                    onNavigateToNewTalkView = {
                        navHostController.navigate(route = Route.NEW_TALK)
                    }
                )
            }

            composable(route = Route.PROFILE) {
                ProfileView(
                    onNavigateBackToTalkListView = {
                        navHostController.popBackStack(
                            route = Route.TALK_LIST,
                            inclusive = false
                        )
                    },
                    onNavigateToLoginView = {
                        navHostController.navigate(route = Route.AUTH) {
                            popUpTo(route = Route.TALKS) {
                                inclusive = true
                            }
                        }
                    }
                )
            }

            composable(route = Route.SETTINGS) {
                SettingsView(
                    onNavigateBackToTalkListView = {
                        navHostController.popBackStack(
                            route = Route.TALK_LIST,
                            inclusive = false
                        )
                    },
                    onNavigateToLoginView = {
                        navHostController.navigate(route = Route.AUTH) {
                            popUpTo(route = Route.TALKS) {
                                inclusive = true
                            }
                        }
                    }
                )
            }

            composable(
                route = "${Route.TALK}/{${Route.TALK_ID_ARGUMENT}}",
                arguments = listOf(navArgument(name = Route.TALK_ID_ARGUMENT) {
                    type = NavType.LongType
                })
            ) {
                TalkView(onNavigateBackToTalkListView = {
                    navHostController.popBackStack(
                        route = Route.TALK_LIST,
                        inclusive = false
                    )
                })
            }

            navigation(
                startDestination = Route.NEW_TALK,
                route = Route.NEW
            ) {
                composable(route = Route.NEW_TALK) {
                    NewTalkView(
                        onNavigateBackToTalkListView = {
                            navHostController.navigate(route = Route.TALKS) {
                                popUpTo(route = Route.NEW) {
                                    inclusive = true
                                }
                            }
                        },
                        onNavigateToTalkView = { talkId: Long ->
                            navHostController.navigate(route = "${Route.TALK}/$talkId") {
                                popUpTo(route = Route.NEW) {
                                    inclusive = true
                                }
                            }
                        },
                        onNavigateToNewGroupTalkView = {
                            navHostController.navigate(route = Route.NEW_GROUP_TALK)
                        }
                    )
                }

                composable(route = Route.NEW_GROUP_TALK) {
                    NewGroupTalkView(
                        onNavigateBackToTalkListView = {
                            navHostController.navigate(route = Route.TALKS) {
                                popUpTo(route = Route.NEW) {
                                    inclusive = true
                                }
                            }
                        },
                        onNavigateToTalkView = { talkId: Long ->
                            navHostController.navigate(route = "${Route.TALK}/$talkId") {
                                popUpTo(route = Route.NEW) {
                                    inclusive = true
                                }
                            }
                        }
                    )
                }
            }
        }
    }
}
