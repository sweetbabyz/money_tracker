package com.example.jizhangbao.data

import com.example.jizhangbao.common.application.App
import com.example.jizhangbao.database.entity.BillEntity
import java.util.Calendar
import java.util.Date

class BiliRepository {

    private val billDao = App.thiz.jzbDatabase.billDao()

    suspend fun addBill(billEntity: BillEntity) {
        billDao.insert(billEntity)
    }

    suspend fun getAll(userId: Int): List<BillEntity> {
        return billDao.selectAll(userId)
    }

    suspend fun getBillsByDate(userId: Int, startDate: Date, endDate: Date): List<BillEntity> {
        return billDao.selectBillsByDate(userId, startDate, endDate)
    }

    suspend fun deleteById(billEntity: BillEntity) {
        return billDao.deleteById(billEntity)
    }


    suspend fun getBillByMonth(userId: Int, date: Date): List<BillEntity> {
        val cal: Calendar = Calendar.getInstance();
        cal.setTime(date)
        cal.set(Calendar.DAY_OF_MONTH, 1)
        val statDate = Date(cal.time.time)
        cal.roll(Calendar.DAY_OF_MONTH, -1)
        val endDate = Date(cal.time.time)

        return billDao.selectBillsByDate(userId, statDate, endDate)
    }

}