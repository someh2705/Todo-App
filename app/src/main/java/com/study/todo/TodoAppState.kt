package com.study.todo

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

@Composable
fun rememberTodoAppState(
    navController: NavHostController = rememberNavController()
) =
    remember(navController) {
        TodoAppState(navController = navController)
    }

@Stable
class TodoAppState(
    val navController: NavHostController,
) {
    val currentRoute : String? get() = navController.currentDestination?.route
}