package com.example.hololiveapp.ui.theme.navigation

sealed class Page(val route: String) {
    object Home : Page("home")
    object Cart : Page("cart")
    object Profile : Page("profile")
    object DetailMerch : Page("home/{merchId}") {
        fun createRoute(merchId: Long) = "home/$merchId"
    }
}