package com.study.todo.ui.page.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.study.todo.model.data.User
import com.study.todo.model.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    repository: UserRepository
) : ViewModel() {
    private var count = 0;
    private val _userInfo: MutableStateFlow<User> = MutableStateFlow(User(name = ""))
    val userInfo: StateFlow<User> = _userInfo

    init {
        viewModelScope.launch {
            repository.getUserInfo().let {
                _userInfo.value = it
            }
        }
    }

    fun change() {
        _userInfo.value = _userInfo.value.copy(
            name = "Todo #${count++}"
        )
    }
}