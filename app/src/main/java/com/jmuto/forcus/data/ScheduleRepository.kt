package com.jmuto.forcus.data

import kotlinx.coroutines.flow.Flow

// DAOをマッピングするクラス
class ScheduleRepository(private val scheduleDao: ScheduleDao) {
    suspend fun insertSchedule(schedule: Schedule) = scheduleDao.insert(schedule)

    suspend fun updateSchedule(schedule: Schedule) = scheduleDao.update(schedule)

    suspend fun deleteSchedule(schedule: Schedule) = scheduleDao.delete(schedule)

    fun getScheduleStream(id: Int): Flow<Schedule?> = scheduleDao.getSchedule(id)

    fun getAllScheduleStreams(): Flow<List<Schedule>> = scheduleDao.getAllSchedules()
}