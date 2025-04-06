package com.speergit.presentation.search

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun SearchScreen(
    onUserSelected: (String) -> Unit,
    viewModel: SearchViewModel = hiltViewModel()
) {
    val state = viewModel.uiState.collectAsState()
    val suggestions = listOf("torvalds", "octocat", "quinnpaterson96", "JakeWharton", "google")

    var input by remember { mutableStateOf("") }
    var showSuggestions by remember { mutableStateOf(false) }

    val filteredSuggestions = remember(input) {
        suggestions.filter { it.contains(input, ignoreCase = true) && it != input }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        OutlinedTextField(
            value = input,
            onValueChange = {
                input = it
                showSuggestions = it.isNotBlank()
            },
            label = { Text("GitHub username") },
            modifier = Modifier.fillMaxWidth()
        )

        if (showSuggestions && filteredSuggestions.isNotEmpty()) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(max = 150.dp)
                    .background(MaterialTheme.colorScheme.surfaceVariant)
                    .padding(vertical = 4.dp)
            ) {
                items(filteredSuggestions) { suggestion ->
                    Text(
                        text = suggestion,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                input = suggestion
                                viewModel.searchUser(suggestion)
                                showSuggestions = false
                            }
                            .padding(8.dp)
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = {
                viewModel.searchUser(input)
                showSuggestions = false
            },
            enabled = input.isNotBlank(),
            modifier = Modifier.align(Alignment.End)
        ) {
            Text("Search")
        }

        Spacer(modifier = Modifier.height(16.dp))

        when (val uiState = state.value) {
            is SearchUiState.Loading -> CircularProgressIndicator()

            is SearchUiState.Success -> {
                val user = uiState.user
                Column {
                    Text("Username: ${user.login}")
                    Text("Name: ${user.name}")
                    Text("Followers: ${user.followers}")
                    Text("Following: ${user.following}")
                    Spacer(modifier = Modifier.height(8.dp))
                    Button(onClick = { onUserSelected(user.login) }) {
                        Text("Go to Profile")
                    }
                }
            }

            is SearchUiState.Error -> {
                Text("User not found", color = Color.Red)
            }

            else -> Unit
        }
    }
}
