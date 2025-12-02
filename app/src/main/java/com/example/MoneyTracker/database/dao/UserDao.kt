package com.example.jizhangbao.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.TypeConverters
import androidx.room.Update
import com.example.jizhangbao.database.converters.DateTypeConvert
import com.example.jizhangbao.database.entity.UserEntity

@Dao
@TypeConverters(DateTypeConvert::class)
interface UserDao {

    @Insert
    suspend fun insert(userEntity: UserEntity)


    @Query("select * from user where username = :username")
    suspend fun selectByUsername(username: String): UserEntity?


    @Update
    suspend fun update(userEntity: UserEntity)

}