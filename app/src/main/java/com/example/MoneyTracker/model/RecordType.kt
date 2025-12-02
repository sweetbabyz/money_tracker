package com.example.jizhangbao.model

import android.graphics.Color
import android.provider.CalendarContract
import com.example.jizhangbao.R

enum class RecordType(val title: String, val icon: Int,val color: Int, val key: Int) {
    OTHER("其他", R.drawable.resource_public, R.color.md_theme_primary, 0),
    DINING("餐饮", R.drawable.ramen_dining, Color.BLUE, 1),
    APPAREL("服饰", R.drawable.apparel, Color.YELLOW, 2),
    EDUCATION("教育", R.drawable.school, Color.CYAN, 3),
    TRANSPORTATION("交通", R.drawable.local_taxi, Color.RED, 4),
    MEDICAL("医疗", R.drawable.medical_services, Color.GREEN, 5),
    EXPRESS_DELIVERY("快递", R.drawable.resource_package, Color.GRAY, 6),
}
