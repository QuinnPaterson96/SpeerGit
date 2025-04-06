package com.speergit.presentation.profile

import androidx.compose.runtime.Composable
import androidx.navigation.NavController

@Composable
fun ProfileScreen(
    username: String,
    navController: NavController,
    viewModel: ProfileViewModel = hiltViewModel()
) {
    val state = viewModel.uiState.collectAsState()

    LaunchedEffect(username) {
        viewModel.loadProfile(username)
    }

    when (val uiState = state.value) {
        is ProfileUiState.Loading -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }

        is ProfileUiState.Success -> {
            val user = uiState.user

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                AsyncImage(
                    model = user.avatarUrl,
                    contentDescription = "Avatar",
                    modifier = Modifier
                        .size(100.dp)
                        .clip(CircleShape)
                )

                Spacer(modifier = Modifier.height(8.dp))
                Text(user.name, style = MaterialTheme.typography.headlineSmall)
                Text("@${user.login}", style = MaterialTheme.typography.bodyMedium)
                Spacer(modifier = Modifier.height(4.dp))
                Text(user.bio, style = MaterialTheme.typography.bodySmall)

                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    ClickableText(
                        text = AnnotatedString("${user.followers} followers"),
                        onClick = {
                            navController.navigate("followers/${user.login}")
                        }
                    )
                    ClickableText(
                        text = AnnotatedString("${user.following} following"),
                        onClick = {
                            navController.navigate("following/${user.login}")
                        }
                    )
                }
            }
        }

        is ProfileUiState.Error -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text("User not found", color = Color.Red)
            }
        }

        else -> Unit
    }
}
