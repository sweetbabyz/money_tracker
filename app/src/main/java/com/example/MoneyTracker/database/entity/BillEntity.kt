package com.example.jizhangbao.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.jizhangbao.database.converters.DateTypeConvert
import com.example.jizhangbao.model.RecordType
import java.util.Date

@Entity("bill")
@TypeConverters(DateTypeConvert::class)
data class BillEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo("user_id")
    val userId: Int,
    @ColumnInfo("money")
    val money: Double,
    @ColumnInfo("remark")
    val remark: String = "",
    @ColumnInfo("type")
    val type: RecordType = RecordType.OTHER,
    @ColumnInfo("date")
    val date: Date = Date(System.currentTimeMillis())

)
