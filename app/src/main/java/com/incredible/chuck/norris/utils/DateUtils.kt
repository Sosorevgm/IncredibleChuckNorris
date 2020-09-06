package com.incredible.chuck.norris.utils

import java.text.SimpleDateFormat
import java.util.*

fun getDateString(date: String): String {
    val dateResult = SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH).parse(date)
    val calendar = Calendar.getInstance()
    calendar.time = dateResult!!
    return getDayOfWeek(calendar.get(Calendar.MONTH)) + " " + calendar.get(Calendar.DAY_OF_MONTH)
}

fun getDayOfWeek(day: Int): String {
    return when (day) {
        0 -> "Jan"
        1 -> "Feb"
        2 -> "Mar"
        3 -> "Apr"
        4 -> "May"
        5 -> "Jun"
        6 -> "Jul"
        7 -> "Aug"
        8 -> "Sept"
        9 -> "Oct"
        10 -> "Nov"
        11 -> "Dec"
        else -> ""
    }
}