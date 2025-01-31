package com.example.arfid


import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.sp
import androidx.compose.material3.Text


@Composable
fun ExpertenregisterScreen() {
    Box(modifier = Modifier
        .fillMaxSize(),
        contentAlignment = Alignment.Center){
        Text(text = "Das Expertennetzwerk",
            fontFamily = FontFamily.SansSerif,
            fontSize = 30.sp)
    }

}
