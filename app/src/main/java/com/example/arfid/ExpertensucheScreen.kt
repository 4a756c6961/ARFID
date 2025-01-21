package com.example.arfid

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.arfid.ui.theme.ARFIDTheme
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState


class ExpertensucheScreenActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ExpertensucheScreen()
        }
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExpertensucheScreen() {
    val context = LocalContext.current
    val expertRepository = remember { ExpertenDaten(context) }

    var experts by remember { mutableStateOf<List<Experten>>(emptyList()) }
    var searchQuery by remember { mutableStateOf("") }

    var showDialog by remember { mutableStateOf(false) }
    var selectedExpert by remember { mutableStateOf<Experten?>(null) }

    LaunchedEffect(true) {
        experts = expertRepository.getExperten()
    }

    val filteredExperts = experts.filter {
        it.name.contains(searchQuery, ignoreCase = true) ||
                it.specialization.contains(searchQuery, ignoreCase = true) ||
                it.city.contains(searchQuery, ignoreCase = true)
    }

    Column(modifier = Modifier.fillMaxSize()) {
        // SearchBar mit Button zum Auslösen der Suche
        SearchBar(
            query = searchQuery,
            onQueryChanged = { searchQuery = it },
            onSearchClicked = {

                println("Suche ausgelöst: $searchQuery")
            }
        )

        // Google Map mit Markern
        GoogleMap(
            modifier = Modifier.fillMaxSize(),
            cameraPositionState = rememberCameraPositionState {
                position = com.google.android.gms.maps.model.CameraPosition.fromLatLngZoom(
                    filteredExperts.firstOrNull()?.location ?: LatLng(54.0886707, 12.1400211),
                    5f
                )
            }
        ) {
            filteredExperts.forEach { expert ->
                Marker(
                    state = MarkerState(position = expert.location),
                    title = expert.name,
                    onClick = {
                        selectedExpert = expert
                        showDialog = true
                        true
                    }
                )
            }
        }

        // AlertDialog für Expertendetails
        if (showDialog && selectedExpert != null) {
            AlertDialog(
                onDismissRequest = { showDialog = false },
                title = { Text(text = "Details zu ${selectedExpert?.name}") },
                text = {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text("Fachgebiet: ${selectedExpert?.specialization}")
                        Text("Adresse: ${selectedExpert?.street} ${selectedExpert?.houseNumber}, ${selectedExpert?.zipCode} ${selectedExpert?.city}")
                        Text(
                            "Telefon: ${selectedExpert?.telephone}",
                            color = Color.Blue,
                            modifier = Modifier.clickable {
                                val intent = Intent(Intent.ACTION_DIAL)
                                intent.data = Uri.parse("tel:${selectedExpert?.telephone}")
                                context.startActivity(intent)
                            }
                        )
                        Text(
                            "E-Mail: ${selectedExpert?.email}",
                            color = Color.Blue,
                            modifier = Modifier.clickable {
                                val intent = Intent(Intent.ACTION_SENDTO)
                                intent.data = Uri.parse("mailto:${selectedExpert?.email}")
                                context.startActivity(intent)
                            }
                        )
                    }
                },
                confirmButton = {
                    TextButton(onClick = { showDialog = false }) {
                        Text("Schließen")
                    }
                }
            )
        }
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar(query: String, onQueryChanged: (String) -> Unit, onSearchClicked: () -> Unit) {
    Row(modifier = Modifier.fillMaxWidth().padding(16.dp)) {
        // TextField für die Eingabe der Suchanfrage
        TextField(
            value = query,
            onValueChange = onQueryChanged,  // Verwende den richtigen Parameter von SearchBar
            placeholder = {
                Text(text = "Suche Experten...")
            },
            modifier = Modifier.weight(1f),
            colors = TextFieldDefaults.textFieldColors(
                focusedIndicatorColor = Color.Blue,
                unfocusedIndicatorColor = Color.Gray
            )
        )

        // Button für die Suche
        Button(
            onClick = onSearchClicked,  // Auslösung der Suche
            modifier = Modifier.padding(start = 8.dp)
        ) {
            Text(text = "Suchen")
        }
    }
}


@Preview
@Composable
fun ExpertensucheScreenPreview() {
    ARFIDTheme {
        ExpertensucheScreen()
    }
}
