package com.example.arfid

import android.app.Activity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.arfid.ui.theme.ARFIDTheme

class ErfahrungsberichtDetailActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val title = intent.getStringExtra("title") ?: "Kein Titel"
        val content = intent.getStringExtra("content") ?: "Kein Inhalt"
        val author = intent.getStringExtra("author") ?: "Unbekannter Autor"
        val date = intent.getStringExtra("date") ?: "Unbekanntes Datum"

        setContent {
            ARFIDTheme {
                ErfahrungsberichtDetailScreen(
                    title = title,
                    content = content,
                    author = author,
                    date = date
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ErfahrungsberichtDetailScreen(
    title: String,
    content: String,
    author: String,
    date: String
) {
    val context = LocalContext.current

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Erfahrungsberichte") },
                navigationIcon = {
                    IconButton(onClick = {
                        (context as? Activity)?.finish()
                    }) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_back),
                            contentDescription = "Schließen"
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFFFFFCFB)
                )
            )
        },
        content = { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(0xFFFFFCFB))
                    .padding(innerPadding)
                    .padding(16.dp)
            ) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.headlineMedium,
                    color = Color(0xFF444444)
                )
                Spacer(modifier = Modifier.height(24.dp))
                Text(
                    text = "Autor: $author",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Gray
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Datum: $date",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Gray
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = content,
                    style = MaterialTheme.typography.bodyLarge,
                    color = Color(0xFF444444)
                )
            }
        }
    )
}
