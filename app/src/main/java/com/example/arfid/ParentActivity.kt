package com.example.arfid

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
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
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        // Weitere Karten
        Spacer(modifier = Modifier.height(16.dp))
        ElevatedCardWithImage(
            title = stringResource(R.string.arfid_verstehen_title),
            subtitle = stringResource(R.string.arfid_verstehen_subtitle),
            imageRes = R.drawable.ic_card_arfid_verstehen
        )
        Spacer(modifier = Modifier.height(16.dp))
        ElevatedCardWithImage(
            title = stringResource(R.string.kind_unterstuetzen_title),
            subtitle = stringResource(R.string.kind_unterstuetzen_subtitle),
            imageRes = R.drawable.ic_card_kind_unterstuetzen
        )
        Spacer(modifier = Modifier.height(16.dp))
        ElevatedCardWithImage(
            title = stringResource(R.string.erfahrungsberichte_title),
            subtitle = stringResource(R.string.erfahrungsberichte_subtitle),
            imageRes = R.drawable.ic_card_erfahrungsberichte
        )
    }
}

@Composable
fun ElevatedCardWithImage(title: String, subtitle: String, imageRes: Int) {
    ElevatedCard(
        modifier = Modifier
            .fillMaxWidth()
            .height(267.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF395F5F)) // Grüne Farbe
    ) {
        Box {
            Image(
                painter = painterResource(id = imageRes),
                contentDescription = title,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
            Column(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(16.dp)
            ) {
                Text(
                    text = title,
                    color = Color.White,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = subtitle,
                    color = Color.White,
                    fontSize = 12.sp
                )
            }
        }
    }
}
