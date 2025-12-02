package com.example.jizhangbao.database.converters

import androidx.room.TypeConverter
import java.sql.Timestamp
import java.util.Date

class DateTypeConvert {
    @TypeConverter
    fun dateToLong(date: Date): Long {
        return date.time
    }

    @TypeConverter
    fun longToDate(value: Long): Date {
        return Date(value)
    }
}