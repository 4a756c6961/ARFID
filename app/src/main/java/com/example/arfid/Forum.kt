package com.example.arfid

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.arfid.ui.theme.ARFIDTheme


@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ForumScreen() {
    val forum = remember {
        ForumDaten().apply {
            loadSampleData()
        }
    }
    val posts = forum.getPosts()

    // Dialog-Steuerung
    var showDialog by remember { mutableStateOf(false) }
    var newTitle by remember { mutableStateOf("") }
    var newContent by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Forum",
                        style = MaterialTheme.typography.headlineMedium,
                        color = MaterialTheme.colorScheme.onPrimary,
                        modifier = Modifier.padding(16.dp)
                    )
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary
                )
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { showDialog = true }, // Dialog anzeigen
                containerColor = MaterialTheme.colorScheme.primary
            ) {
                Text("+", color = MaterialTheme.colorScheme.onPrimary)
            }
        }
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
                .padding(16.dp)
        ) {
            Spacer(modifier = Modifier.height(32.dp)) // Abstand oben
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                items(posts) { post ->
                    PostItem(post = post)
                }
            }
        }

        // Dialog für Beitragserstellung
        if (showDialog) {
            AddPostDialog(
                onDismiss = { showDialog = false },
                onAddPost = { title, content ->
                    forum.addPost(title, content, "Benutzer")
                    showDialog = false
                },
                newTitle = newTitle,
                onTitleChange = { newTitle = it },
                newContent = newContent,
                onContentChange = { newContent = it }
            )
        }
    }
}

@Composable
fun AddPostDialog(
    onDismiss: () -> Unit,
    onAddPost: (String, String) -> Unit,
    newTitle: String,
    onTitleChange: (String) -> Unit,
    newContent: String,
    onContentChange: (String) -> Unit
) {
    AlertDialog(
        onDismissRequest = { onDismiss() },
        confirmButton = {
            TextButton(onClick = {
                if (newTitle.isNotBlank() && newContent.isNotBlank()) {
                    onAddPost(newTitle, newContent)
                }
            }) {
                Text("Hinzufügen")
            }
        },
        dismissButton = {
            TextButton(onClick = { onDismiss() }) {
                Text("Abbrechen")
            }
        },
        title = { Text("Neuen Beitrag hinzufügen") },
        text = {
            Column {
                TextField(
                    value = newTitle,
                    onValueChange = onTitleChange,
                    label = { Text("Titel") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(16.dp))
                TextField(
                    value = newContent,
                    onValueChange = onContentChange,
                    label = { Text("Inhalt") },
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    )
}

@Composable
fun PostItem(post: Post) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = post.title, style = MaterialTheme.typography.titleMedium)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = post.content, style = MaterialTheme.typography.bodyMedium)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Von: ${post.author}, ${post.timestamp}", style = MaterialTheme.typography.bodySmall)
        }
    }
}

@Preview
@Composable
fun ForumScreenPreview() {
    ARFIDTheme {
        ForumScreen()
    }
}

