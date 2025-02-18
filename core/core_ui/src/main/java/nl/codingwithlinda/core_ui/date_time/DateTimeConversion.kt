package nl.codingwithlinda.core_ui.date_time

import android.annotation.SuppressLint
import java.time.Instant
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle

@SuppressLint("NewApi")
fun timestampToString(timestamp: Long): String{
    val instant = Instant.ofEpochMilli(timestamp)

   return ZonedDateTime.ofInstant(instant, ZoneId.systemDefault()).format(DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM))
}