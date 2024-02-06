package com.jmuto.forcus.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

// DAOは、テーブルにCRUD処理をするためのインターフェース
// suspend関数は非同期処理をする
@Dao
interface ScheduleDao {

    // insert()をKotlinコードから呼び出すと、RoomはSQLクエリを実行してデータをテーブルに挿入する
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(schedule: Schedule)

    // scheduleと同じprimary keyを持つデータが更新される
    @Update
    suspend fun update(schedule: Schedule)

    @Delete
    suspend fun delete(schedule: Schedule)

    // 戻り値の型としてFlowを指定すると、テーブル内のデータが変更されるたびに通知が届く
    // Room がこの Flow を最新の状態に維持するので、データを明示的に取得する必要があるのは一度だけ
    @Query("SELECT * FROM schedule WHERE id = :id")
    fun getSchedule(id: Int): Flow<Schedule?>

    // これも、データを明示的に取得する必要があるのは一度だけ
    @Query("SELECT * FROM schedule")
    fun getAllSchedules(): Flow<List<Schedule>>
}