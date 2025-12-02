package com.example.jizhangbao.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.jizhangbao.database.converters.DateTypeConvert
import java.util.Date

@Entity("user")
@TypeConverters(DateTypeConvert::class)
data class UserEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val username: String,
    val password: String,
    val regDate: Date = Date(System.currentTimeMillis()),
)
