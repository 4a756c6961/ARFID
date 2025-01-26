package com.example.arfid

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.arfid.ui.theme.ARFIDTheme
import java.text.SimpleDateFormat
import java.util.*

class ErfahrungsberichtErstellenActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ARFIDTheme {
                ErfahrungsberichtErstellenScreen(
                    onCancel = { finish() },
                    onSubmit = { title, subtitle, content, author ->
                        val currentDate = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault()).format(Date())
                        val resultIntent = Intent().apply {
                            putExtra("title", title)
                            putExtra("subtitle", subtitle)
                            putExtra("content", content)
                            putExtra("author", author)
                            putExtra("date", currentDate)
                        }
                        setResult(Activity.RESULT_OK, resultIntent)
                        finish()
                    }
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ErfahrungsberichtErstellenScreen(
    onCancel: () -> Unit,
    onSubmit: (title: String, subtitle: String, content: String, author: String) -> Unit
) {
    var title by remember { mutableStateOf("") }
    var subtitle by remember { mutableStateOf("") }
    var content by remember { mutableStateOf("") }
    var author by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Erfahrungsbericht einreichen") },
                navigationIcon = {
                    IconButton(onClick = onCancel) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_back),
                            contentDescription = "Zurück"
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0xFFFFFCFB))
            )
        },
        content = { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .background(Color(0xFFFFFCFB))
                    .padding(16.dp),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    // Titel-Input
                    OutlinedTextField(
                        value = title,
                        onValueChange = { title = it },
                        label = { Text("Titel Ihres Berichts") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp)
                    )

                    // Subtitle-Input
                    OutlinedTextField(
                        value = subtitle,
                        onValueChange = { subtitle = it },
                        label = { Text("Untertitel Ihres Berichts") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp)
                    )

                    // Inhalt-Input
                    OutlinedTextField(
                        value = content,
                        onValueChange = { content = it },
                        label = { Text("Schreiben Sie hier Ihre Erfahrung...") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp)
                            .padding(vertical = 8.dp)
                    )

                    // Autor-Input
                    OutlinedTextField(
                        value = author,
                        onValueChange = { author = it },
                        label = { Text("Ihr Name") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp)
                    )
                }

                // Footer Buttons
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    OutlinedButton(
                        onClick = onCancel,
                        colors = ButtonDefaults.outlinedButtonColors(
                            containerColor = Color.Transparent,
                            contentColor = Color(0xFFFF7350)
                        ),
                        border = BorderStroke(2.dp, Color(0xFFFF7350)),
                        shape = RoundedCornerShape(24.dp),
                        modifier = Modifier.weight(1f).padding(end = 8.dp)
                    ) {
                        Text("Verwerfen")
                    }

                    Button(
                        onClick = { onSubmit(title, subtitle, content, author) },
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFF7350)),
                        shape = RoundedCornerShape(24.dp),
                        modifier = Modifier.weight(1f).padding(start = 8.dp)
                    ) {
                        Text("Einreichen")
                    }
                }
            }
        }
    )
}
