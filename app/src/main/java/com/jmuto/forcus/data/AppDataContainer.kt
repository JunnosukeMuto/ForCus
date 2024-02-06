package com.jmuto.forcus.data

import android.content.Context

// データベースをインスタンス化する
class AppDataContainer(private val context: Context) {
    val scheduleRepository = ScheduleRepository(ForCusDatabase.getDatabase(context).scheduleDao())
}