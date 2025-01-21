package com.example.arfid

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
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
        bottomBar = { ParentBottomNavigationBar(navController) }
    ) { innerPadding ->
        val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            // Begrüßungstext nur anzeigen, wenn die Route "home" ist
            if (currentRoute == "home") {
                Text(
                    text = "Willkommen in der App! Hier findest du alle wichtigen Informationen.",
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth()
                )
            }

            // Navigation Host
            NavHost(
                navController = navController,
                startDestination = "home",
                modifier = Modifier.weight(1f)
            ) {
                composable("home") {  }
                composable("wissen") { WissenScreen() }
                composable("expertensuche") { ExpertensucheScreen() }
                composable("forum") { ForumScreen() }
            }
        }
    }
}

@Composable
fun ParentBottomNavigationBar(navController: NavController) {
    val navItems = listOf(
        ParentNavItem("home", "Home", R.drawable.ic_home),
        ParentNavItem("wissen", "Wissen", R.drawable.ic_wissen),
        ParentNavItem("expertensuche", "Experten", R.drawable.ic_suche),
        ParentNavItem("forum", "Forum", R.drawable.ic_forum)
    )

    NavigationBar {
        val context = LocalContext.current
        val currentBackStackEntry = navController.currentBackStackEntryAsState().value
        val currentRoute = currentBackStackEntry?.destination?.route

        navItems.forEach { item ->
            NavigationBarItem(
                selected = currentRoute == item.route,
                onClick = { if (item.route == "home") {
                    // Intent für MainActivity auslösen
                    val intent = Intent(context, MainActivity::class.java).apply {
                        flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
                    }
                    context.startActivity(intent)
                } else {
                    // Navigation zu einer anderen Route
                    navController.navigate(item.route) {
                        popUpTo(navController.graph.startDestinationId) { saveState = true }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
                },
                icon = {
                    Icon(
                        painter = painterResource(id = item.iconId),
                        contentDescription = null,
                        modifier = Modifier.size(24.dp)
                    )
                },
                label = { Text(text = item.label) }
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