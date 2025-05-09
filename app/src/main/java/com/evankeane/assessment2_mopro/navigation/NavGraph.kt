package com.evankeane.assessment2_mopro.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.evankeane.assessment2_mopro.ui.theme.screen.FormKendaraan
import com.evankeane.assessment2_mopro.ui.theme.screen.KEY_ID_KENDARAAN
import com.evankeane.assessment2_mopro.ui.theme.screen.MainScreen
import com.evankeane.assessment2_mopro.ui.theme.screen.RecycleScreen

@Composable
fun SetupNavGraph(navController: NavHostController = rememberNavController()) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {
        composable(route = Screen.Home.route) {
            MainScreen(navController)
        }
        composable(route = Screen.Recycle.route) {
            RecycleScreen(navController)
        }
        composable(route = Screen.FormTambah.route) {
            FormKendaraan(navController)
        }
        composable(
            route = Screen.FormUbah.route,
            arguments = listOf(
                navArgument(KEY_ID_KENDARAAN) { type = NavType.LongType }
            )
        ) { navBackStackEntry ->
            val id = navBackStackEntry.arguments?.getLong(KEY_ID_KENDARAAN)
            FormKendaraan(navController, id)
        }
    }
}