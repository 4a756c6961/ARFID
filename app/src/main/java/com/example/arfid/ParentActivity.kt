package com.example.arfid

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.arfid.ui.theme.ARFIDTheme


class ParentActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ARFIDTheme {
                ParentScreen()
            }
        }
    }
}

@Composable
fun ParentScreen() {
    val navController = rememberNavController()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = { ParentBottomNavigationBar(navController)
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = "wissen",
            modifier = Modifier.padding(innerPadding)
        ) {
            composable("wissen") { ParentHomeScreen() }
            composable("expertensuche") { ExpertensucheScreen() }
            composable("forum") { ForumScreen() }
        }
    }
}

@Composable
fun ParentHomeScreen() {
    val context = LocalContext.current
    val scrollState = rememberScrollState()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xFFFFFCFB))
            .verticalScroll(scrollState)
            .padding(16.dp)
    ) {
        ElevatedCardContents(
            imageRes = R.drawable.arfid_verstehen,
            title = stringResource(id = R.string.title_arfid_verstehen),
            subtitle = stringResource(id = R.string.subtitle_arfid_verstehen),
            onClick = {
            val intent = Intent(context, ScreenArfidVerstehen01::class.java)
            context.startActivity(intent)})

        Spacer(modifier = Modifier.height(16.dp))
        ElevatedCardContents(
            imageRes = R.drawable.kind_unterstuetzen,
            title = stringResource(id = R.string.title_kind_unterstuetzen),
            subtitle = stringResource(id = R.string.subtitle_kind_unterstuetzen),
            onClick = {val intent = Intent(context, ScreenKindUnterstuetzen01::class.java)
                context.startActivity(intent)})

        Spacer(modifier = Modifier.height(16.dp))
        ElevatedCardContents(
            imageRes = R.drawable.erfahrungsberichte,
            title = stringResource(id = R.string.title_erfahrungsberichte),
            subtitle = stringResource(id = R.string.subtitle_erfahrungsberichte),
            onClick = {val intent = Intent(context, ErfahrungsberichteActivity::class.java)
                context.startActivity(intent)})
    }
}

@Composable
fun ElevatedCardContents(imageRes: Int, onClick: () -> Unit, title: String, subtitle: String ) {
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

@Composable
fun ParentBottomNavigationBar(navController: NavController) {
    val navItems = listOf(
        ParentNavItem("home", "Home", R.drawable.ic_home),
        ParentNavItem("wissen", "Wissen", R.drawable.ic_wissen),
        ParentNavItem("expertensuche", "Expertensuche", R.drawable.ic_suche),
        ParentNavItem("forum", "Forum", R.drawable.ic_forum)
    )

    NavigationBar (
        modifier = Modifier
        .border(1.dp, Color.LightGray),
        containerColor = Color(0xFFFFFCFB),
        contentColor = Color.Black
    ) {
        val currentBackStackEntry = navController.currentBackStackEntryAsState().value
        val currentRoute = currentBackStackEntry?.destination?.route

        navItems.forEach { item ->
            NavigationBarItem(
                selected = currentRoute == item.route,
                onClick = {
                    navController.navigate(item.route) {
                        popUpTo(navController.graph.startDestinationId) { saveState = true }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                icon = {
                    Icon(
                        painter = painterResource(id = item.iconId),
                        contentDescription = null,
                        modifier = Modifier.size(24.dp)
                    )
                },
                label = { Text(text = item.label) },

                        colors = NavigationBarItemDefaults.colors(
                        indicatorColor = Color(0xFFF2EBE6)
                        )

            )
        }
    }
}

data class ParentNavItem(
    val route: String,
    val label: String,
    val iconId: Int
)

@Preview(showBackground = true)
@Composable
fun ParentScreenPreview() {
    ARFIDTheme {
        ParentScreen()
    }
}