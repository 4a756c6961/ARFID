package com.example.arfid


import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp


@Composable
fun WissenScreen() {
    val context = LocalContext.current
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(16.dp)
    ) {
        ElevatedCardContent(
            imageRes = R.drawable.arfid_verstehen,
            title = stringResource(id = R.string.title_arfid_verstehen),
            subtitle = stringResource(id = R.string.subtitle_arfid_verstehen),
            onClick = {
                val intent = Intent(context, ScreenArfidVerstehen01::class.java)
                context.startActivity(intent)})

        Spacer(modifier = Modifier.height(16.dp))
        ElevatedCardContent(
            imageRes = R.drawable.kind_unterstuetzen,
            title = stringResource(id = R.string.title_kind_unterstuetzen),
            subtitle = stringResource(id = R.string.subtitle_kind_unterstuetzen),
            onClick = {val intent = Intent(context, ScreenKindUnterstuetzen01::class.java)
                context.startActivity(intent)})

        Spacer(modifier = Modifier.height(16.dp))
        ElevatedCardContent(
            imageRes = R.drawable.erfahrungsberichte,
            title = stringResource(id = R.string.title_erfahrungsberichte),
            subtitle = stringResource(id = R.string.subtitle_erfahrungsberichte),
            onClick = {})
    }
}

@Composable
fun ElevatedCardContent(imageRes: Int, onClick: () -> Unit, title: String, subtitle: String ) {
    ElevatedCard(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 5.dp)
            .clickable { onClick() },
        colors = CardDefaults.elevatedCardColors(
            containerColor = Color(0xFF375E5E)
        ),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.elevatedCardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier.padding(0.dp)
        ) {
            Image(
                painter = painterResource(id = imageRes),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(170.dp),
                contentScale = ContentScale.Crop
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp)
            ) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold),
                    color = Color.White
                )
                Text(
                    text = subtitle,
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.White
                )
                Spacer(modifier = Modifier.height(8.dp))

            }
        }
    }
}