package com.example.arfid

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.arfid.ui.theme.ARFIDTheme

data class Erfahrungsbericht(
    val id: Int,
    val title: String,
    val subtitle: String,
    val content: String,
    val author: String,
    val date: String
)

class ErfahrungsberichteActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val berichte = mutableStateListOf(
            Erfahrungsbericht(
                id = 1,
                title = "Wie wir stressfreie Mahlzeiten eingeführt haben",
                subtitle = "Tipps für ein stressfreies Abendessen",
                content = "Detaillierter Bericht über unsere Erfahrungen...",
                author = "Max Gross",
                date = "25.11.2024"
            ),
            Erfahrungsbericht(
                id = 2,
                title = "Unser Weg zu neuen Lebensmitteln",
                subtitle = "Wie wir neue Lebensmittel integriert haben",
                content = "Ein weiterer Bericht über Erfolge und Rückschläge...",
                author = "Anna Müller",
                date = "12.10.2024"
            )
        )

        val launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val data = result.data
                if (data != null) {
                    val title = data.getStringExtra("title") ?: "Kein Titel"
                    val subtitle = data.getStringExtra("subtitle") ?: "Kein Untertitel"
                    val content = data.getStringExtra("content") ?: "Kein Inhalt"
                    val author = data.getStringExtra("author") ?: "Unbekannter Autor"
                    val date = data.getStringExtra("date") ?: "Heute"

                    berichte.add(
                        Erfahrungsbericht(
                            id = berichte.size + 1,
                            title = title,
                            subtitle = subtitle,
                            content = content,
                            author = author,
                            date = date
                        )
                    )
                }
            }
        }

        setContent {
            ARFIDTheme {
                ErfahrungsberichteScreen(
                    berichte = berichte,
                    onCreateClick = {
                        val intent = Intent(this, ErfahrungsberichtErstellenActivity::class.java)
                        launcher.launch(intent)
                    }
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ErfahrungsberichteScreen(
    berichte: List<Erfahrungsbericht>,
    onCreateClick: () -> Unit
) {
    val context = LocalContext.current
    Scaffold(
        topBar = {
            TopAppBar(
                title = {},
                navigationIcon = {
                    IconButton(onClick = {
                        val intent = Intent(context, ParentActivity::class.java)
                        context.startActivity(intent)
                        if (context is Activity) {
                            context.finish()
                        }
                    }) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_close),
                            contentDescription = "Schließen"
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFFB4D1D1)
                )
            )
        },
        content = { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(0xFFFFFCFB))
                    .padding(innerPadding)
                    .verticalScroll(rememberScrollState())
            ) {
                HeaderSection()

                berichte.forEach { bericht ->
                    ErfahrungsberichtCard(bericht = bericht, context = context)
                }

                FooterSection(onCreateClick = onCreateClick)
            }
        }
    )
}

@Composable
fun HeaderSection() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFFFFFCFB))
    ) {
        Image(
            painter = painterResource(id = R.drawable.header_erfahrungsberichte),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(16f / 10f)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = stringResource(id = R.string.headline_erfahrungsberichte),
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(horizontal = 16.dp)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = stringResource(id = R.string.intro_erfahrungsberichte),
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(16.dp),
            color = Color.Gray
        )
    }
}

@Composable
fun ErfahrungsberichtCard(bericht: Erfahrungsbericht, context: Context) {
    ElevatedCard(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp, horizontal = 16.dp),
        colors = CardDefaults.elevatedCardColors(containerColor = Color(0xFFFDF4F2)),
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                text = bericht.title,
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Medium,
                color = Color(0xFF444444)
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = bericht.subtitle,
                style = MaterialTheme.typography.bodyMedium,
                color = Color(0xFF444444)
            )
            Spacer(modifier = Modifier.weight(1f))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                OutlinedButton(
                    onClick = {
                        val intent = Intent(context, ErfahrungsberichtDetailActivity::class.java).apply {
                            putExtra("title", bericht.title)
                            putExtra("subtitle", bericht.subtitle)
                            putExtra("content", bericht.content)
                            putExtra("author", bericht.author)
                            putExtra("date", bericht.date)
                        }
                        context.startActivity(intent)
                    },
                    colors = ButtonDefaults.outlinedButtonColors(contentColor = Color(0xFFFF7350)),
                    border = BorderStroke(2.dp, Color(0xFFFF7350)),
                    shape = RoundedCornerShape(24.dp)
                ) {
                    Text("Mehr lesen")
                }
            }
        }
    }
}

@Composable
fun FooterSection(onCreateClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = onCreateClick,
            shape = RoundedCornerShape(24.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFF7350)),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Eigenen Erfahrungsbericht einreichen")
        }
    }
}
