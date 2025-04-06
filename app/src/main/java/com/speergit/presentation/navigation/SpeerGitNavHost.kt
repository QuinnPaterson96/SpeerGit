package com.speergit.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.speergit.presentation.profile.ProfileScreen
import com.speergit.presentation.search.SearchScreen

@Composable
fun SpeerGitNavHost(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = "search"
    ) {
        composable("search") {
            SearchScreen(
                onUserSelected = { username ->
                    navController.navigate("profile/$username")
                }
            )
        }


        composable("profile/{username}") { backStackEntry ->
            val username = backStackEntry.arguments?.getString("username") ?: ""
            ProfileScreen(username = username, navController = navController)
        }
        /*
        composable("followers/{username}") { backStackEntry ->
            val username = backStackEntry.arguments?.getString("username") ?: ""
            FollowersScreen(username = username)
        }

        composable("following/{username}") { backStackEntry ->
            val username = backStackEntry.arguments?.getString("username") ?: ""
            FollowingScreen(username = username)
        }

         */
    }
}
