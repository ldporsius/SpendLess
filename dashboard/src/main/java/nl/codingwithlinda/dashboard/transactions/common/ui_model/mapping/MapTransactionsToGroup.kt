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

enum class DayDiff{
    TODAY, YESTERDAY, OLDER
}
fun dayToUiText(dayDiff: DayDiff): UiText{
   return when (dayDiff) {
       DayDiff.TODAY -> UiText.DynamicText("Today")
       DayDiff.YESTERDAY -> UiText.DynamicText("Yesterday")
       else -> UiText.DynamicText("Older")
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
fun List<Transaction>.groupByDate(): List<TransactionGroup>{
    println("MAP TRANSACTIONS TO GROUP: ORIGINAL = $this")

    val dayValueMap = this.groupBy {
        getDayFromTimestamp(it.timestamp)
    }
   .map{
        TransactionGroup(
            date = dayToDayDiff(it.key),
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