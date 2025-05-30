package com.picpay.desafio.android.presentation.common.navigation.graph

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.picpay.desafio.android.presentation.common.navigation.route.Route
import com.picpay.desafio.android.presentation.home.ui.screen.PicPayScreen

@Composable
internal fun NavigationGraph(
    navController: NavHostController,
    startDestination: Route = Route.Home,
) {
    NavHost(navController, startDestination) {
        composable<Route.Home> {
            PicPayScreen()
        }
    }
}