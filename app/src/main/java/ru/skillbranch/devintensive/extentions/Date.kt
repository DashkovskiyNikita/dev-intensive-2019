package ru.skillbranch.devintensive.extentions

import android.view.SoundEffectConstants
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.abs

const val SECOND = 1000L
const val MINUTE = 60 * SECOND
const val HOUR = 60 * MINUTE
const val DAY = 24 * HOUR
 var num = 0
 var unit: TimeUnits = TimeUnits.SECOND
var comes : Int = 0
fun Date.format(pattern: String = "HH:mm:ss dd:MM:yy"): String {
    val dateFormat: SimpleDateFormat = SimpleDateFormat(pattern, Locale("ru"))
    return dateFormat.format(this)
}

fun Date.add(value: Int, units: TimeUnits = TimeUnits.SECOND): Date {
    comes++
    num = value
    unit = units
    var time = this.time
    time += when (units) {
        TimeUnits.SECOND -> value * SECOND
        TimeUnits.MINUTE -> value * MINUTE
        TimeUnits.HOUR -> value * HOUR
        TimeUnits.DAY -> value * DAY
    }
    this.time = time
    return this
}

fun Date.humanizeDiff(date: Date = Date()): String {
    if(comes == 0){
        num = 0
        unit = TimeUnits.SECOND
    }
    var counter = true
    val list1 = listOf(1, 21, 31, 41)
    val list2 = listOf(2, 3, 4, 22, 23, 24, 32, 33, 34, 42, 43, 44)
    val list3 = listOf(5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,25,26,27,28,29,30,35,36,37,38,39,40,45)
    while (counter) {
        when (unit) {
            TimeUnits.SECOND -> if (num / 60 >= 1) {
                num /= 60
                unit = TimeUnits.MINUTE
            } else counter = false
            TimeUnits.MINUTE -> if (num / 60 >= 1) {
                num /= 60
                unit = TimeUnits.HOUR
            } else counter = false
            TimeUnits.HOUR -> if(num / 60 >= 1) {
                num /= 60
                unit = TimeUnits.DAY
            } else counter = false
            TimeUnits.DAY -> counter = false
        }

    }
    comes = 0
    return when (unit) {
        TimeUnits.SECOND -> when (num) {
            in 0..1 -> "только что"
            in 1..45 -> "несколько секунд назад"
            in 45..75 -> "минуту назад"
            else -> "0"
        }
        TimeUnits.MINUTE -> when (num) {
            in list1 -> "$num минуту назад"
            in list2 -> "$num минуты назад"
            in list3 -> "$num минут назад"
            else -> "0"
        }
        TimeUnits.HOUR -> when (num) {
            in list1 -> "$num час назад"
            in list2 -> "$num часа назад"
            in list3 -> "$num часов назад"
            else -> "0"
        }
        TimeUnits.DAY -> when (num) {
            in list1 -> "$num день назад"
            in list2 -> "$num дня назад"
            in list3 -> "$num дней назад"
            360 -> "год назад"
            else -> "более года назад"
        }

    }

}


enum class TimeUnits {
    SECOND,
    MINUTE,
    HOUR,
    DAY

}