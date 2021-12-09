package com.study.todo.model.repository

import com.study.todo.model.data.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async

class UserRepository {
    suspend fun getUserInfo() : User {
        val user = CoroutineScope(Dispatchers.IO).async {
            User(
                userId = 1,
                name = "Todo"
            )
        }

        return user.await()
    }
}