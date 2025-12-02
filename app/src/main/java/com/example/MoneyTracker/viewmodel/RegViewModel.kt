package com.example.jizhangbao.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jizhangbao.data.UserRepository
import com.example.jizhangbao.database.entity.UserEntity
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class RegViewMode : ViewModel() {

    private val userRepository = UserRepository()

    private val _regUserResult = MutableStateFlow<RegUserResult>(RegUserResult.Idle)

    val regUserResult = _regUserResult.asStateFlow()


    fun regUser(userEntity: UserEntity) {
        viewModelScope.launch {
            userRepository.getByUsername(userEntity.username)?.apply {
                _regUserResult.value = RegUserResult.Failure("该用户已经存在")
            } ?: apply {
                userRepository.addUser(userEntity)
                _regUserResult.value = RegUserResult.Success
            }
        }
    }


}

sealed class RegUserResult {
    data object Idle : RegUserResult()
    data object Success : RegUserResult()
    data class Failure(val msg: String) : RegUserResult()

}