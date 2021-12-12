package com.study.todo

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.study.todo.ui.Screen
import com.study.todo.ui.page.home.HomeBody
import com.study.todo.ui.page.home.HomeViewModel
import com.study.todo.ui.theme.TodoTheme

@Composable
fun TodoApp() {
    TodoTheme {
        val appState = rememberTodoAppState()
        NavHost(appState.navController, startDestination = Screen.HOME.route) {
            composable(Screen.HOME.route) {
                val homeViewModel: HomeViewModel = hiltViewModel()
                HomeBody(homeViewModel)
            }
        }
    }
}