package com.example.jizhangbao.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.jizhangbao.database.dao.BillDao
import com.example.jizhangbao.database.dao.UserDao
import com.example.jizhangbao.database.entity.BillEntity
import com.example.jizhangbao.database.entity.UserEntity


@Database(entities = [BillEntity::class, UserEntity::class], version = 1, exportSchema = false)
abstract class JZBDatabase : RoomDatabase() {

    abstract fun billDao(): BillDao
    abstract fun userDao(): UserDao

    companion object {
        @Volatile
        private var INSTANCE: JZBDatabase? = null
        fun getDatabase(context: Context): JZBDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    JZBDatabase::class.java,
                    "jzb_db"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }

}