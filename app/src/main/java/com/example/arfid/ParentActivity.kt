package com.example.arfid

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.arfid.ui.theme.ARFIDTheme

class ParentActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ARFIDTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    ParentScreen()
                }
            }
        }
    }
}

@Composable
fun ParentScreen() {
    Column(modifier = Modifier.padding(16.dp)) {
        ElevatedCardArfidVerstehen()
        Spacer(modifier = Modifier.height(16.dp))
        ElevatedCardKindUnterstuetzen()
        Spacer(modifier = Modifier.height(16.dp))
        ElevatedCardErfahrungsberichte()
    }
}

@Composable
fun ElevatedCardArfidVerstehen() {
    ElevatedCard(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 20.dp, start = 5.dp, end = 5.dp),
        colors = CardDefaults.elevatedCardColors(
            containerColor = Color.White
        )
    ) {
        Column(
            modifier = Modifier.padding(5.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.arfid_verstehen),
                contentDescription = "ARFID verstehen",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            )
        }
    }
}

@Composable
fun ElevatedCardKindUnterstuetzen() {
    ElevatedCard(
        elevation = CardDefaults.cardElevation( // Hier wird die elevation korrekt gesetzt
            defaultElevation = 6.dp // Beispielhöhe
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 20.dp, start = 5.dp, end = 5.dp),
        colors = CardDefaults.elevatedCardColors(
            containerColor = Color.White
        )
    ) {
        Column(
            modifier = Modifier.padding(5.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.kind_unterstuetzen),
                contentDescription = "Kind unterstützen",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            )
        }
    }
}

@Composable
fun ElevatedCardErfahrungsberichte() {
    ElevatedCard(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 20.dp, start = 5.dp, end = 5.dp),
        colors = CardDefaults.elevatedCardColors(
            containerColor = Color.White
        ),

    ) {
        Column(
            modifier = Modifier.padding(5.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.erfahrungsberichte),
                contentDescription = "ARFID verstehen",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ParentScreenPreview() {
    ARFIDTheme {
        ParentScreen()
    }
}
