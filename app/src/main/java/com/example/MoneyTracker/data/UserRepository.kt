package com.example.jizhangbao.data

import com.example.jizhangbao.common.application.App
import com.example.jizhangbao.database.entity.UserEntity

class UserRepository {

    private val userDao = App.thiz.jzbDatabase.userDao()

    suspend fun addUser(userEntity: UserEntity) {
        return userDao.insert(userEntity)
    }

    suspend fun getByUsername(username: String): UserEntity? {
        return userDao.selectByUsername(username)
    }


}