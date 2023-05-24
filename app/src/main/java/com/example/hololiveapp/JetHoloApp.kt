package com.example.hololiveapp

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.hololiveapp.ui.theme.HololiveAppTheme
import com.example.hololiveapp.ui.theme.navigation.Page
import com.example.hololiveapp.ui.theme.page.cart.CartPage
import com.example.hololiveapp.ui.theme.page.home.HomePage
import com.example.hololiveapp.ui.theme.navigation.NavMember
import com.example.hololiveapp.ui.theme.page.about.AboutPage
import com.example.hololiveapp.ui.theme.page.detail.DetailPage

@Composable
fun JetHoloApp(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Scaffold(
        bottomBar = {
            if (currentRoute != Page.DetailMerch.route) {
                BottomBar(navController)
            }
        },
        modifier = modifier
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Page.Home.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Page.Home.route) {
                HomePage(navigateToDetail = { merchId ->
                    navController.navigate(Page.DetailMerch.createRoute(merchId))
                })
            }
            composable(Page.Cart.route) {
                val context = LocalContext.current
                CartPage(onOrderButtonClicked = { message ->
                    buyMerch(context, message)
                })
            }
            composable(Page.Profile.route) {
                AboutPage()
            }
            composable(
                route = Page.DetailMerch.route,
                arguments = listOf(navArgument("merchId") { type = NavType.LongType }),
            ) {
                val id = it.arguments?.getLong("merchId") ?: -1L
                DetailPage(
                    MerchId = id,
                    navigateBack = {
                        navController.navigateUp()
                    }
                ) {
                    navController.popBackStack()
                    navController.navigate(Page.Cart.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            }
        }
    }
}

@Composable
private fun BottomBar(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    BottomNavigation(
        modifier = modifier
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        val navigationItems = listOf(
            NavMember(
                name = stringResource(R.string.Home),
                icon = Icons.Default.Home,
                page = Page.Home,
                desc = "home_page"
            ),
            NavMember(
                name = stringResource(R.string.Cart),
                icon = Icons.Default.ShoppingCart,
                page = Page.Cart,
                desc = "cart_page"
            ),
            NavMember(
                name = stringResource(R.string.Profile),
                icon = Icons.Default.AccountCircle,
                page = Page.Profile,
                desc = "about_page"
            ),
        )
        BottomNavigation {
            navigationItems.map { item ->
                BottomNavigationItem(
                    icon = {
                        Icon(
                            imageVector = item.icon,
                            contentDescription = item.desc,
                        )
                    },
                    label = { Text(item.name) },
                    selected = currentRoute == item.page.route,
                    onClick = {
                        navController.navigate(item.page.route)
                    }
                )
            }
        }
    }
}

private fun buyMerch(context: Context, summary: String) {
    val intent = Intent(Intent.ACTION_SEND).apply {
        type = "text/plain"
        putExtra(Intent.EXTRA_SUBJECT, context.getString(R.string.money))
        putExtra(Intent.EXTRA_TEXT, summary)
    }
    context.startActivity(
        Intent.createChooser(
            intent,
            context.getString(R.string.money)
        )
    )
}

@Preview(showBackground = true)
@Composable
fun holoMerchAppPreview() {
    HololiveAppTheme() {
        JetHoloApp()
    }
}


