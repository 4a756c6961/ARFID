package com.example.arfid
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.arfid.ui.theme.ARFIDTheme


class ChildActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ARFIDTheme {

                ChildScreen()
            }
        }
    }
}


@Composable
fun ChildScreen() {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = { ChildBottomNavigationBar(navController) }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = "home_child",
            modifier = Modifier.padding(innerPadding)
        ) {
            composable("home_child") { ChildActivity() }
            composable("arfid_verstehen") { ArfidVerstehenScreen() }
            composable("spiele") { SpieleScreen()}
            composable("kinderforum") { KinderForumScreen() }
        }
    }
}

@Composable
fun ChildBottomNavigationBar(navController: NavController) {
    val navItems = listOf(
        ChildNavItem("home_child", "Home", R.drawable.ic_home),
        ChildNavItem("arfid_verstehen", "Entdecken", R.drawable.ic_arfid),
        ChildNavItem("spiele", "Spielen", R.drawable.ic_spielen),
        ChildNavItem("kinderforum", "Forum", R.drawable.ic_forum)
    )

    NavigationBar {
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
                icon = { Icon(
                    painterResource(id = item.iconId),
                    contentDescription = item.label,
                    modifier = Modifier.size(20.dp)
                )
                },
                label = { Text(text = item.label) }
            )
        }
    }

}

data class ChildNavItem(
    val route: String,
    val label: String,
    val iconId: Int
)


@Preview(showBackground = true)
@Composable
fun GreetingPreview2() {
    ARFIDTheme {
        ChildScreen()
    }
}