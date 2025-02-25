package nl.codingwithlinda.core_ui.date_time

import android.annotation.SuppressLint
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle

@SuppressLint("NewApi")
enum class DateTimeConverter {
    MEDIUM_DATE {
        override fun convert(zonedDateTime: ZonedDateTime): String {
            return zonedDateTime.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM))
        }

    },
    SHORT_DATE {
        override fun convert(zonedDateTime: ZonedDateTime): String {
            return zonedDateTime.format(DateTimeFormatter.ofPattern("MMMM dd"))
        }
    },
    ;


    abstract fun convert(zonedDateTime: ZonedDateTime): String
}