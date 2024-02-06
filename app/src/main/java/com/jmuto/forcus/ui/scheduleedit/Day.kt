package com.jmuto.forcus.ui.scheduleedit

import androidx.annotation.StringRes
import com.jmuto.forcus.R

enum class Day(@StringRes val shortDayId: Int, @StringRes val DayId: Int) {
    SUN(R.string.short_sun, R.string.sun),
    MON(R.string.short_mon, R.string.mon),
    TUE(R.string.short_tue, R.string.tue),
    WED(R.string.short_wed, R.string.wed),
    THU(R.string.short_thu, R.string.thu),
    FRI(R.string.short_fri, R.string.fri),
    SAT(R.string.short_sat, R.string.sat),
}