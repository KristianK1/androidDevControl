package hr.kristiankliskovic.devcontrol.utils

import android.util.Log
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.abs

fun valuesToCalendar(
    year: Int,
    month: Int,
    day: Int,
    hour: Int = 0,
    minute: Int = 0,
    second: Int = 0,
): Calendar {
    return Calendar.getInstance().apply {
        set(Calendar.YEAR, year)
        set(Calendar.MONTH, month - 1)
        set(Calendar.DAY_OF_MONTH, day)
        set(Calendar.HOUR_OF_DAY, hour)
        set(Calendar.MINUTE, minute)
        set(Calendar.SECOND, second)
    }
}

fun CalendarToIso(c: Calendar): String {
    return SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'").format(c.time)
}

fun ISOtoCalendar(iso: String): Calendar {
    val sdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")
    val date = sdf.parse(iso)
    val cal = Calendar.getInstance()
    cal.time = date
    return cal
}


fun CalendarToTSinputFormat(c: Calendar): String {
    return SimpleDateFormat("yyyy-MM-dd'%20'HH:mm:ss").format(c.time)
}


fun ISOtoTSinputFormat(iso: String): String {
    return CalendarToTSinputFormat(ISOtoCalendar(iso))
}

fun UnixToCalendar(unixTime: Long): Calendar? {
    val calendar: Calendar = Calendar.getInstance()
    calendar.timeInMillis = unixTime * 1000
    return calendar
}

fun CalendarToUnix(cal: Calendar): Long {
    val timeInSec = cal.timeInMillis / 1000
    return timeInSec
}

fun isostring_toLocalCalendar(iso: String): Calendar {
    val UNIX = CalendarToUnix(ISOtoCalendar(iso))

    val sdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ", Locale.JAPAN)

    val formattedDate: String = sdf.format(UNIX * 1000)

    var result: Calendar = Calendar.getInstance()
    try {
        val yyyy = formattedDate.subSequence(0, 4).toString().toInt()
        val MM = formattedDate.subSequence(5, 7).toString().toInt()
        val dd = formattedDate.subSequence(8, 10).toString().toInt()

        val HH = formattedDate.subSequence(11, 13).toString().toInt()
        val mm = formattedDate.subSequence(14, 16).toString().toInt()
        val ss = formattedDate.subSequence(17, 19).toString().toInt()

        val offsetHours = formattedDate.subSequence(20, 22).toString().toInt()
        val offsetMinutes = formattedDate.subSequence(22, 24).toString().toInt()

        result = Calendar.getInstance().apply {
            set(Calendar.YEAR, yyyy)
            set(Calendar.MONTH, MM - 1)
            set(Calendar.DAY_OF_MONTH, dd)
            set(Calendar.HOUR_OF_DAY, HH + offsetHours)
            set(Calendar.MINUTE, mm + offsetMinutes)
            set(Calendar.SECOND, ss)
        }
    } catch (e: Throwable) {
        Log.i("dayLight", "error")
    }
    return result

}

fun localCalendarToGMTISO(
    calendar: Calendar,
): String {
    val deltaM = getTimeZoneOffsetInMinutes(calendar)
    val calendarShifted = Calendar.getInstance().apply {
        set(Calendar.YEAR, calendar.get(Calendar.YEAR))
        set(Calendar.MONTH, calendar.get(Calendar.MONTH))
        set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH))
        set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR_OF_DAY))
        set(Calendar.MINUTE, calendar.get(Calendar.MINUTE) - deltaM)
        set(Calendar.SECOND, calendar.get(Calendar.SECOND))
    }
    return CalendarToIso(calendarShifted);
}

fun addTimeToCalendar(
    calendar: Calendar,
    years: Int = 0,
    months: Int = 0,
    days: Int = 0,
    hours: Int = 0,
    mins: Int = 0,
    seconds: Int = 0,
): Calendar {
    val calOut = Calendar.getInstance().apply {
        set(Calendar.YEAR, calendar.get(Calendar.YEAR) + years)
        set(Calendar.MONTH, calendar.get(Calendar.MONTH) + months)
        set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH) + days)
        set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR_OF_DAY) + hours)
        set(Calendar.MINUTE, calendar.get(Calendar.MINUTE) + mins)
        set(Calendar.SECOND, calendar.get(Calendar.SECOND) + seconds)
    }
    return calOut
}

fun getTimeZoneOffsetInMinutes(calendar: Calendar): Int {
    val timeZone = TimeZone.getDefault()
    val offsetInMilliseconds = timeZone.getOffset(calendar.timeInMillis)
    return offsetInMilliseconds / (1000 * 60) // Convert to minutes
}

fun getLocalTimeForDisplayFromISO(iso: String): String {
    val calendar = ISOtoCalendar(iso)
    val timeZoneOffset = getTimeZoneOffsetInMinutes(calendar)
    val shiftedTime = addTimeToCalendar(
        calendar = calendar,
        mins = timeZoneOffset,
    )
    val sign = if (timeZoneOffset >= 0) "+" else "-"
    return String.format(
        "%02d/%02d/%04d %02d:%02d (GMT${sign}%d:%02d)",
        shiftedTime.get(Calendar.DAY_OF_MONTH),
        shiftedTime.get(Calendar.MONTH) + 1,
        shiftedTime.get(Calendar.YEAR),
        shiftedTime.get(Calendar.HOUR_OF_DAY),
        shiftedTime.get(Calendar.MINUTE),
        abs(timeZoneOffset / 60),
        abs(timeZoneOffset % 60)
    )
}
