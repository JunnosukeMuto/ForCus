package com.jmuto.forcus.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.jmuto.forcus.ui.scheduleedit.Day
import com.jmuto.forcus.ui.scheduleedit.Feature
import java.time.LocalTime


// ローカルで動くSQLiteのテーブル定義
@Entity(tableName = "schedule")
data class Schedule(

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val isEnabled: Boolean = true,
    val selectedFeature: Feature,
    val fromTime: LocalTime,
    val toTime: LocalTime,
    val sun: Boolean,
    val mon: Boolean,
    val tue: Boolean,
    val wed: Boolean,
    val thu: Boolean,
    val fri: Boolean,
    val sat: Boolean
) {
    fun isEveryday(): Boolean {
        return sun&&mon&&tue&&wed&&thu&&fri&&sat
    }

    fun isOnce(): Boolean {
        return !(sun||mon||tue||wed||thu||fri||sat)
    }

    fun selectedDays(): List<Day> {
        val days: MutableList<Day> = mutableListOf()
        listOf(sun,mon,tue,thu,fri,sat).forEachIndexed { index, b ->
            if (b) {
                days.add(Day.entries[index])
            }
        }
        return days.toList()
    }
}