package com.example.jizhangbao.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jizhangbao.data.BiliRepository
import com.example.jizhangbao.data.UserRepository
import com.example.jizhangbao.database.entity.UserEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {

    private val userRepository = UserRepository()

    private var _loginUserInfo: MutableStateFlow<LoginResult> = MutableStateFlow(LoginResult.Idle)

    val loginUserInfo: StateFlow<LoginResult> = _loginUserInfo.asStateFlow()


    fun loginUser(username: String, password: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val user = userRepository.getByUsername(username)
            if (user != null && user.password == password) {
                _loginUserInfo.value = LoginResult.Success(user)
            } else {
                _loginUserInfo.value = LoginResult.Failure
            }
        }

    }
}


sealed class LoginResult {
    data object Idle : LoginResult()
    data class Success(val userEntity: UserEntity) : LoginResult()
    data object Failure : LoginResult()

}