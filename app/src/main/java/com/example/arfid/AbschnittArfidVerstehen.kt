@file:OptIn(ExperimentalMaterial3Api::class)
package com.example.arfid

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.arfid.ui.theme.ARFIDTheme
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class AbschnittArfidVerstehen : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ARFIDTheme {
                AbschnittArfidVerstehenScreen()
            }
        }
    }
}

@Composable
fun AbschnittArfidVerstehenScreen() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    // Leere Box, da der Titel nicht benötigt wird (wie im Screenshot)
                },
                navigationIcon = {
                    IconButton(onClick = { /* Aktion für Schließen */ }) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_close),
                            contentDescription = "Schließen"
                        )
                    }
                },
                actions = {
                    IconButton(onClick = { /* Aktion für Teilen */ }) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_share),
                            contentDescription = "Teilen"
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFFB4D1D1),
                ),
                modifier = Modifier.fillMaxWidth()
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(Color(0xFFFFFCFB))
        ) {
            // Bild oben
            Image(
                painter = painterResource(id = R.drawable.header_arfid_verstehen), // Header-Bild
                contentDescription = "ARFID verstehen",
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(16f / 12f),
                        contentScale = ContentScale.Crop
            )

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp) // Padding an den Seiten
            ) {
                Text(
                    text = stringResource(id = R.string.title_Slide_1),
                    style = MaterialTheme.typography.headlineMedium,
                    modifier = Modifier.padding(top = 16.dp, bottom = 8.dp) // Optional: zusätzliches Padding oben/unten
                )
                Text(
                    text = stringResource(id = R.string.content_Slide_1)
                )
            }

            Spacer(modifier = Modifier.weight(1f))

            // Buttons unten
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp, horizontal = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Button(
                    onClick = { /* Zurück-Aktion */ },
                    modifier = Modifier
                        .weight(1f)
                        .padding(end = 8.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFECECEC),
                        contentColor = Color.Gray
                    ),
                    shape = RoundedCornerShape(100.dp)
                ) {
                    Text(text = "Zurück")
                }

                Button(
                    onClick = { /* Weiter-Aktion */ },
                    modifier = Modifier
                        .weight(1f)
                        .padding(start = 8.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFFF7350),
                        contentColor = Color.White
                    ),
                    shape = RoundedCornerShape(100.dp)
                ) {
                    Text(text = "Weiter")
                }
            }
        }
    }
}