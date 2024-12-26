package com.example.arfid.ui.theme

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.arfid.R

sealed class BottomNavigationBar (val title:String, val icon:Int, val route:String) {
    object ueber_arfid : BottomNavigationBar("Über ARFID", R.drawable.ic_arfid, "Über ARFID")
    object expertennezwerk : BottomNavigationBar("Expertennetzwerk", R.drawable.ic_expertennetzwerk, "Expertennetzwerk")
    object nachrichten : BottomNavigationBar("Nachrichten", R.drawable.ic_nachricht, "Nachrichten")
    object forum: BottomNavigationBar("Forum", R.drawable.ic_forum, "Forum")
    object entdecken: BottomNavigationBar("Entdecken", R.drawable.ic_entdecken, "Entdecken")
    object spielen: BottomNavigationBar("Spielen", R.drawable.ic_spielen, "Spielen")

}


@Composable
fun BottomNavigationBar(navController: NavController) {

    val items = listOf(
        BottomNavigationBar.ueber_arfid,
        BottomNavigationBar.expertennezwerk,
        BottomNavigationBar.nachrichten,
        BottomNavigationBar.forum,
        BottomNavigationBar.entdecken,
        BottomNavigationBar.spielen
    )

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    NavigationBar {
        items.forEach { item ->
            NavigationBarItem(
                icon = { Icon(
                    painterResource(id = item.icon),
                    contentDescription = item.title)

                     },
                label = { Text(item.title) },
                selected = currentRoute == item.route,
                onClick = {
                    navController.navigate(item.route) {
                        // Pop up to the root destination of the graph to
                        // avoid building up a large stack of destinations
                        // on the back stack as users select items
                        navController.graph.startDestinationRoute?.let { route ->
                            popUpTo(route) {
                                saveState = true
                            }
                        }
                        // Avoid multiple copies of the same destination when
                        // reselecting the same item
                        launchSingleTop = true
                        // Restore state when reselecting a previously selected item
                        restoreState = true
                    }
                }
            )
        }
    }
}