package com.example.arfid

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavBackStackEntry
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.compose.material3.Text

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    Scaffold(bottomBar = {
        NavigationBar {
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentDestination: NavBackStackEntry? = navBackStackEntry?.destination

            listOfNavItems.forEach {navItems ->
                NavigationBarItem(
                    selected = currentDestination?.hierarchy?.any { it.route == navItems.route } == true,
                    onClick = {
                        navController.navigate(navItems.route){
                            popUpTo(navController.graph.findStartDestination().id){
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true


                        }
                    },
                    label = {
                        Text(text = navItems.label)
                    },
                    icon = {
                        Icon(imageVector = navItems.icon,
                            contentDescription = null)
                    },

                )

            }
        }
    }
    ) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = Screens.ueber_arfid_screen,
            modifier = Modifier.padding(paddingValues)

        ) {
            composable(route = Screens.ueber_arfid_screen.name) {
                UeberArfidScreen()
            }
            composable(route = Screens.NachrichtenScreen.name) {
                NachrichtenScreen()
            }
            composable(route = Screens.ExpertennetzwerkScreen.name) {
                ExpertennetzwerkScreen()
            }

        }
    }
}
