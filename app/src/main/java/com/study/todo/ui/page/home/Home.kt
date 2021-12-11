package com.study.todo.ui.page.home

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.study.todo.model.data.User
import com.study.todo.ui.theme.TodoTheme

@Composable
fun HomeBody(
    homeViewModel: HomeViewModel
) {
    val user by homeViewModel.userInfo.collectAsState()
    val onClick = remember {
        { homeViewModel.change() }
    }
    HomeBody(user = user, onClick = onClick)
}

@Composable
fun HomeBody(
    user: User,
    onClick: () -> Unit
) {
    Log.d("onClick", onClick.hashCode().toString())
    Scaffold {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            Button(onClick = onClick) {
                Text(text = user.name)
            }
        }
    }
}

@Preview
@Composable
fun HomeBodyPreview() {
    TodoTheme {
        HomeBody(user = User(name = "Todo")) {

        }
    }
}