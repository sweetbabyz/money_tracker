package com.example.jizhangbao.common.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity


open class BaseActivity : AppCompatActivity() {
    private val activities: MutableList<BaseActivity> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activities.add(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        activities.remove(this)
    }

    fun finishAllActivities() {
        for (activity in activities) {
            if (!activity.isFinishing) {
                activity.finish()
            }
        }
    }
}