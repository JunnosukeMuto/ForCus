package com.jmuto.forcus.data

import androidx.room.TypeConverter
import java.time.LocalTime
import java.time.format.DateTimeFormatter

// LocalTimeとかMapを、データベースに読み込ませるためのシンプルな型に変換する
class Converters {
    @TypeConverter
    fun localTimeToString(time: LocalTime): String {
        return time.format(DateTimeFormatter.ISO_TIME)
    }

    @TypeConverter
    fun stringToLocalTime(string: String): LocalTime {
        return LocalTime.parse(string, DateTimeFormatter.ISO_TIME)
    }
}