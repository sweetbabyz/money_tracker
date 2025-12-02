package com.example.jizhangbao.common.application

import android.app.Activity
import android.app.Application
import com.example.jizhangbao.database.JZBDatabase
import com.example.jizhangbao.database.entity.UserEntity

class App : Application() {

    companion object {
        lateinit var thiz: App
        var loginUserId: Int = 0
    }

    val jzbDatabase: JZBDatabase by lazy { JZBDatabase.getDatabase(this) }

    override fun onCreate() {
        super.onCreate()
        thiz = this
    }


}