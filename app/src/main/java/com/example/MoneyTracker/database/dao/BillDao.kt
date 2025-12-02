package com.example.jizhangbao.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.TypeConverters
import com.example.jizhangbao.database.converters.DateTypeConvert
import com.example.jizhangbao.database.entity.BillEntity
import java.util.Date

@Dao
@TypeConverters(DateTypeConvert::class)
interface BillDao {

    @Insert
    suspend fun insert(billEntity: BillEntity)

    @Query("select * from bill where user_id = :userId")
    suspend fun selectAll(userId:Int): List<BillEntity>

    @Query("SELECT * FROM bill WHERE date >= :startDate and date <= :endDate and user_id = :userId")
    suspend fun selectBillsByDate(userId:Int,startDate: Date, endDate: Date): List<BillEntity>

    @Delete
    suspend fun deleteById(billEntity: BillEntity)
}