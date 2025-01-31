package com.example.arfid

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.arfid.ui.theme.ARFIDTheme
import androidx.compose.material3.Text as Text1


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ARFIDTheme {
                Scaffold { innerPadding ->
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                            .background(color = Color(0xFFFFFCFB)),
                    ) {
                        Column(
                            modifier = Modifier
                                .weight(1f)
                                .padding(16.dp),
                            verticalArrangement = Arrangement.spacedBy(16.dp)
                        ) {
                            ElevatedCardGreeting()
                            ElevatedCardKind()
                            ElevatedCardEltern()
                            ElevatedCardDoctor()
                        }

                    }
                }
            }
        }
    }
}


@Composable
fun ElevatedCardGreeting() {
    ElevatedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),
        colors = CardDefaults.elevatedCardColors(containerColor = Color(0xFFF2EBE6)),
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp)

    ) {
        Text1(
            text = stringResource(R.string.greeting),
            modifier = Modifier
                .padding(16.dp),
            textAlign = TextAlign.Left,
        )
    }
}


@Composable
fun ElevatedCardKind() {
    val context = LocalContext.current
    ElevatedCard(
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
        colors = CardDefaults.elevatedCardColors(containerColor = Color(0xFF395F5F)),
        modifier = Modifier
            .fillMaxWidth()
            .height(160.dp)
            .clickable {
                val intent = Intent(context, ChildActivity::class.java)
                context.startActivity(intent)
            }
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 16.dp)
            ) {

                Text1(
                    text = stringResource(R.string.card_kind_headline),
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    ),
                    modifier = Modifier.padding(bottom = 8.dp)
                )

                Text1(
                    text = stringResource(R.string.card_kind_subheadline),
                    style = MaterialTheme.typography.bodyMedium.copy(
                        color = Color.White
                    )
                )
            }

            Image(
                painter = painterResource(id = R.drawable.kinder), 
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxHeight()
                    .width(160.dp)
            )
        }
    }
}




@Composable
fun ElevatedCardDoctor() {
    val context = LocalContext.current
    ElevatedCard(
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
        colors = CardDefaults.elevatedCardColors(containerColor = Color(0xFF395F5F)),
        modifier = Modifier
            .fillMaxWidth()
            .height(160.dp)
            .clickable {
                val intent = Intent(context, DoctorActivity::class.java)
                context.startActivity(intent)
            }
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 16.dp)
            ) {

                Text1(
                    text = stringResource(R.string.card_fachkraefte_headline),
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    ),
                    modifier = Modifier.padding(bottom = 8.dp)
                )

                Text1(
                    text = stringResource(R.string.card_fachkraefte_subheadline),
                    style = MaterialTheme.typography.bodyMedium.copy(
                        color = Color.White
                    )
                )
            }

            Image(
                painter = painterResource(id = R.drawable.fachkraefte),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxHeight()
                    .width(160.dp)
            )
        }
    }
}


@Composable
fun ElevatedCardEltern() {
    val context = LocalContext.current
    ElevatedCard(
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
        colors = CardDefaults.elevatedCardColors(containerColor = Color(0xFF395F5F)),
        modifier = Modifier
            .fillMaxWidth()
            .height(160.dp)
            .clickable {
                val intent = Intent(context, ParentActivity::class.java)
                context.startActivity(intent)
            }
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 16.dp)
            ) {

                Text1(
                    text = stringResource(R.string.card_eltern_headline),
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    ),
                    modifier = Modifier.padding(bottom = 8.dp)
                )

                Text1(
                    text = stringResource(R.string.card_eltern_subheadline),
                    style = MaterialTheme.typography.bodyMedium.copy(
                        color = Color.White
                    )
                )
            }

            Image(
                painter = painterResource(id = R.drawable.eltern),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxHeight()
                    .width(160.dp)
            )
        }
    }
}



@Preview(showBackground = true)
@Composable
fun ElevatedCardPreview() {
    ARFIDTheme {
        Column {
            ElevatedCardEltern()
            ElevatedCardKind()
            ElevatedCardDoctor()
        }
    }
}


