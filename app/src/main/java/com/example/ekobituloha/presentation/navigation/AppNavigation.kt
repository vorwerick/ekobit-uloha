package com.example.ekobituloha.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.ekobituloha.presentation.screens.ObjectDetailScreen
import com.example.ekobituloha.presentation.screens.ObjectListScreen

object AppDestinations {
    const val OBJECT_LIST = "objectList"
    const val OBJECT_DETAIL = "objectDetail"
    const val OBJECT_ID_KEY = "objectId"

    fun objectDetailRoute(objectId: Int) = "$OBJECT_DETAIL/$objectId"
}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = AppDestinations.OBJECT_LIST
    ) {
        composable(AppDestinations.OBJECT_LIST) {
            ObjectListScreen(
                onObjectClick = { objectId ->
                    navController.navigate(AppDestinations.objectDetailRoute(objectId))
                }
            )
        }

        composable(
            route = "${AppDestinations.OBJECT_DETAIL}/{${AppDestinations.OBJECT_ID_KEY}}",
            arguments = listOf(
                navArgument(AppDestinations.OBJECT_ID_KEY) {
                    type = NavType.IntType
                }
            )
        ) { backStackEntry ->
            val objectId = backStackEntry.arguments?.getInt(AppDestinations.OBJECT_ID_KEY) ?: -1
            ObjectDetailScreen(
                objectId = objectId,
                onBackClick = { navController.popBackStack() }
            )
        }
    }
}
