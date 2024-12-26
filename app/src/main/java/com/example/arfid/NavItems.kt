package com.example.arfid


data class NavItems(
    val label: String,
    val iconId: Int,
    val route: String
)

val listOfNavItems = listOf(
    NavItems(
        label = "Expertennetzwerk",
        iconId = R.drawable.ic_expertennetzwerk,
        route = Screens.ExpertennetzwerkScreen.name
    ),
    NavItems(
        label = "Nachrichten",
        iconId = R.drawable.ic_nachricht,
        route = Screens.NachrichtenScreen.name
    ),
    NavItems(
        label = "Über ARFID",
        iconId = R.drawable.ic_arfid,
        route = Screens.ueber_arfid_screen.name
    ),
)