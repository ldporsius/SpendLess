package nl.codingwithlinda.dashboard.transactions.common.ui_model.mapping

import android.annotation.SuppressLint
import nl.codingwithlinda.core.domain.model.Preferences
import nl.codingwithlinda.core.domain.model.Transaction
import nl.codingwithlinda.core_ui.currency.CurrencyFormatterFactory
import nl.codingwithlinda.core_ui.util.UiText
import nl.codingwithlinda.dashboard.transactions.common.ui_model.TransactionGroup
import nl.codingwithlinda.dashboard.transactions.common.ui_model.TransactionGroupUi
import java.time.Instant
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle

sealed interface TransactionKey{
    data class Simple(val dayDiff: DayDiff): TransactionKey {
        override fun isOlder(): Boolean {
           return when(dayDiff){
               DayDiff.TODAY -> false
               DayDiff.YESTERDAY -> false
               DayDiff.OLDER -> true
           }
        }
    }

    data class Detailed(val dayGroup: DayGroup): TransactionKey {
        override fun isOlder(): Boolean {
            return false
        }
    }

    fun isOlder(): Boolean
}

data class DayGroup(val daydiff: Int, val date: Long)

@SuppressLint("NewApi")
fun getDayGroupFromTimestamp(timestamp: Long): DayGroup {
    val today = ZonedDateTime.now().dayOfYear
    val date = Instant.ofEpochMilli(timestamp)
    val day = date.atZone(ZoneId.systemDefault()).dayOfYear
    val diff = today - day
    return DayGroup(diff, timestamp)
}

fun List<Transaction>.groupByDateGroup(): List<TransactionGroup>{
    println("MAP TRANSACTIONS TO GROUP: ORIGINAL = $this")

    val dayValueMap = this.groupBy {
        getDayGroupFromTimestamp(it.timestamp)
    }
        .map{
            TransactionGroup(
                date = TransactionKey.Detailed(it.key),
                transactions = it.value
            )
        }

    return dayValueMap
}



enum class DayDiff{
    TODAY, YESTERDAY, OLDER
}
@SuppressLint("NewApi")
fun dayToUiText(transactionKey: TransactionKey): UiText{
    when(transactionKey){
        is TransactionKey.Detailed -> {
            return when(transactionKey.dayGroup.daydiff){
                0 -> UiText.DynamicText("Today")
                1 -> UiText.DynamicText("Yesterday")
                else -> {
                    val parsedDate = Instant.ofEpochMilli(transactionKey.dayGroup.date).let {instant ->
                        ZonedDateTime.ofInstant(instant, ZoneId.systemDefault()).format(
                            DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM)
                        )

                    }
                    UiText.DynamicText(parsedDate)
                }
            }
        }
        is TransactionKey.Simple -> {
            val dayDiff = transactionKey.dayDiff
            return when (dayDiff) {
                DayDiff.TODAY -> UiText.DynamicText("Today")
                DayDiff.YESTERDAY -> UiText.DynamicText("Yesterday")
                else -> UiText.DynamicText("Older")
            }

        }
    }

}

fun dayToDayDiff(day: Int): DayDiff{
    return when(day){
        0 -> DayDiff.TODAY
        1 -> DayDiff.YESTERDAY
        else -> DayDiff.OLDER
    }
}
@SuppressLint("NewApi")
fun getDayFromTimestamp(timestamp: Long): Int {
    val today = ZonedDateTime.now().dayOfYear
    val date = Instant.ofEpochMilli(timestamp)
    val day = date.atZone(ZoneId.systemDefault()).dayOfYear
    val diff = today - day
    return diff
}

@SuppressLint("NewApi")
fun getPreviousWeek(
    today: ZonedDateTime = ZonedDateTime.now(),
    timestamp: Long): Int{
    val dayOfYearToday = today.dayOfYear
    val dayOfWeekToday = today.dayOfWeek.value
    val date = Instant.ofEpochMilli(timestamp)
    val dayOfYearAtTimestamp = date.atZone(ZoneId.systemDefault()).dayOfYear
    val dayOfWeekAtTimestamp = date.atZone(ZoneId.systemDefault()).dayOfWeek.value

    val diffDayOfYear = dayOfYearToday - dayOfYearAtTimestamp
    val diffDayOfWeek = dayOfWeekToday - dayOfWeekAtTimestamp
    println("day of week today: $dayOfWeekToday, day of week at timestamp: $dayOfWeekAtTimestamp")
    println("Diff day of year: $diffDayOfYear, diff day of week: $diffDayOfWeek")
    val dayRange = diffDayOfWeek + diffDayOfYear
    return dayRange
}

fun List<Transaction>.groupByDate(): List<TransactionGroup>{
    println("MAP TRANSACTIONS TO GROUP: ORIGINAL = $this")

    val dayValueMap = this.groupBy {
        getDayFromTimestamp(it.timestamp)
    }
   .map{
        TransactionGroup(
            date = TransactionKey.Simple(dayToDayDiff(it.key)),
            transactions = it.value
        )
    }

    return dayValueMap
}

fun List<TransactionGroup>.toUi(
    currencyFormatterFactory: CurrencyFormatterFactory,
    preferences: Preferences
): List<TransactionGroupUi> {

   return this.map { transactionGroup ->

        TransactionGroupUi(
            header = dayToUiText(transactionGroup.date),
            transactions = transactionGroup.transactions.map { transaction ->
                transaction.toUi(currencyFormatterFactory, preferences)
            }
        )
    }
}