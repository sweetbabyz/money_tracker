package com.example.jizhangbao.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.jizhangbao.R
import com.example.jizhangbao.databinding.ItemBilDateBinding
import com.example.jizhangbao.model.BiliDate
import java.util.Calendar
import java.util.Date

class BiliDateAdapter : RecyclerView.Adapter<ViewHolder>() {

    var biliDates = listOf<BiliDate>()

    var totalMoney = 0.0

    var onSelectDate: ((Date) -> Unit)? = null

    val thisYear = 2025

    var selectIndex = -1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemBilDateBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return biliDates.size
    }

    @SuppressLint("ResourceAsColor")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val binding = holder.binding as ItemBilDateBinding
        val dateInfo = biliDates[position]
        // 180
        binding.apply {

            if(selectIndex == -1){
                selectIndex = biliDates.size - 1
            }

            val resources = mainLy.context.resources
            val defaultHeight = (100 * resources.displayMetrics.density).toInt()
            date.text = "${dateInfo.month}月"

            money.text = dateInfo.money.toString()

            if (dateInfo.money > 0) {
                // 假设dateInfo.money和totalMoney是你已经定义好的变量
                val moneyRatio = dateInfo.money / totalMoney

                val dpHeight = (moneyRatio * 110)

                val pxHeight = (dpHeight * resources.displayMetrics.density).toInt()

                mainLy.layoutParams.height = defaultHeight + pxHeight
            }else{
                mainLy.layoutParams.height = defaultHeight
            }

            if (position == selectIndex) {
                mainLy.setBackgroundColor(root.context.resources.getColor(R.color.md_theme_primaryContainer))
            } else {
                mainLy.setBackgroundColor(android.R.color.transparent);
            }

            mainLy.setOnClickListener {
                onSelectDate?.apply {
                    selectIndex = position
                    invoke(getFirstDayOfMonth(thisYear, dateInfo.month.toInt()))
                    notifyDataSetChanged()
                }
            }

        }

    }


    private fun getFirstDayOfMonth(year: Int, month: Int): Date {
        val calendar = Calendar.getInstance()
        calendar.clear()
        calendar.set(year, month - 1, 1) // 减1是因为月份是从0开始计数的
        return calendar.time
    }


}