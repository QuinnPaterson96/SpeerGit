package com.speergit

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.speergit.presentation.navigation.SpeerGitNavHost


@Composable
fun SpeerGitApp() {
    val navController = rememberNavController()

    MaterialTheme { // Or use your custom theme if you want
        SpeerGitNavHost(navController = navController)
    }
}