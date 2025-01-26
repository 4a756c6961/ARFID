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


class DoctorActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ARFIDTheme {

                DocScreen()
                }
            }
        }
    }


@Composable
fun DocScreen() {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = { DocBottomNavigationBar(navController) }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = "home",
            modifier = Modifier.padding(innerPadding)
        ) {
            composable("home") { DoctorActivity() }
            composable("ueber_arfid") { UeberArfidScreen() }
            composable("expertenregister") { ExpertenregisterScreen()}
            composable("expertenforum") { ExpertenforumScreen() }
        }
    }
}

@Composable
fun DocBottomNavigationBar(navController: NavController) {
    val navItems = listOf(
        NavItem("home", "Home", R.drawable.ic_home),
        NavItem("ueber_arfid", "Über ARFID", R.drawable.ic_arfid),
        NavItem("expertenregister", "Register", R.drawable.ic_expertennetzwerk),
        NavItem("expertenforum", "Forum", R.drawable.ic_forum)
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

data class NavItem(
    val route: String,
    val label: String,
    val iconId: Int
)

@Composable
fun Greeting1(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Herzlich Willkommen $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview1() {
    ARFIDTheme {
        Greeting1("BesucherInnen")
        DocScreen()
    }
}