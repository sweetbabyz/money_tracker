package com.example.jizhangbao.viewmodel

import android.annotation.SuppressLint
import android.icu.util.Calendar
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jizhangbao.common.application.App
import com.example.jizhangbao.data.BiliRepository
import com.example.jizhangbao.database.entity.BillEntity
import com.example.jizhangbao.model.BiliDate
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date

class BookFragmentViewModel : ViewModel() {

    private val biliRepository = BiliRepository()

    private val _biliFlow = MutableStateFlow(listOf<BillEntity>())

    val biliFlow: StateFlow<List<BillEntity>> = _biliFlow

    private val _biliDatesFlow = MutableStateFlow(listOf<BiliDate>())

    val biliDatesFlow: StateFlow<List<BiliDate>> = _biliDatesFlow

    private val _billDateDate = MutableStateFlow(Date(System.currentTimeMillis()))

    val billDateDate: StateFlow<Date> = _billDateDate

    suspend fun updateBillData(date: Date = billDateDate.value) {
        viewModelScope.launch(Dispatchers.IO) {
            _billDateDate.value = date
            _biliFlow.value = biliRepository.getBillByMonth(userId = App.loginUserId,date = date)
        }
    }


    @SuppressLint("SimpleDateFormat")
    suspend fun initDateInfo() {
        // 格式化日期备用
        val format = SimpleDateFormat("MM")
        // 获取当前日期备用
        val calendar = Calendar.getInstance()
        val dateSetMap = mutableMapOf<String, Double>()
        val biliDates = mutableListOf<BiliDate>()

        // 获取下今年第一月第一天到现在的月份最后一天
        val startDate = Calendar.getInstance().run {
            set(Calendar.DAY_OF_YEAR, 1)
            time
        }
        val endDate = Calendar.getInstance().run {
            set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH))
            time
        }

        // 初始化Map备用
        (format.format(startDate.time).toInt()..format.format(endDate.time).toInt()).forEach {
            dateSetMap[it.toString()] = 0.0
        }

        viewModelScope.launch(Dispatchers.IO) {
            // 获取全部数据
            val biliList = biliRepository.getBillsByDate(userId = App.loginUserId,startDate, endDate)

            biliList.forEach {
                // 格式化日期，去掉日期的0
                val month = format.format(it.date).let { month ->
                    if (month.startsWith("0")) {
                        month.substring(1)
                    } else {
                        month
                    }
                }

                // 分组处理金额
                dateSetMap[month] = dateSetMap[month]?.run { this + it.money } ?: it.money
            }

            // 构造出分组查询日期的结果（避免在SQL分组日期）
            dateSetMap.forEach { (t, u) ->
                biliDates.add(BiliDate(t, u))
            }

            _biliDatesFlow.value = biliDates

        }


    }

    fun deleteById(billEntity: BillEntity) {
        viewModelScope.launch {
            biliRepository.deleteById(billEntity)
            updateData()
        }
    }


    fun addBill(entity: BillEntity) {
        viewModelScope.launch {
            biliRepository.addBill(entity)
            updateData()
        }
    }

    suspend fun updateData() {
        updateBillData()
        initDateInfo()
    }

}