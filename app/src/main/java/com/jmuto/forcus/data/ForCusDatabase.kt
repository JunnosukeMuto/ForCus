package com.jmuto.forcus.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [Schedule::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class ForCusDatabase : RoomDatabase() {

    // データベースにDAOを認識させる
    abstract fun scheduleDao(): ScheduleDao

    companion object {
        // データベースのインスタンスを保持する変数
        @Volatile
        private var Instance: ForCusDatabase? = null

        // データベースのインスタンスを返す。無ければインスタンスを作る
        fun getDatabase(context: Context): ForCusDatabase {
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, ForCusDatabase::class.java, "forcus_database")
                    .build()
                    .also { Instance = it }
            }
        }
    }
}