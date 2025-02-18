package nl.codingwithlinda.dashboard.transactions.presentation.ui_model.mapping

import android.annotation.SuppressLint
import nl.codingwithlinda.core.domain.model.Preferences
import nl.codingwithlinda.core.domain.model.Transaction
import nl.codingwithlinda.core_ui.currency.CurrencyFormatterFactory
import nl.codingwithlinda.core_ui.util.UiText
import nl.codingwithlinda.dashboard.transactions.presentation.ui_model.TransactionGroup
import nl.codingwithlinda.dashboard.transactions.presentation.ui_model.TransactionGroupUi
import java.time.Instant
import java.time.ZoneId
import java.time.ZonedDateTime

fun dayToUiText(dayDiff: Int): UiText{
   return when (dayDiff) {
       0 -> UiText.DynamicText("Today")
       1 -> UiText.DynamicText("Yesterday")
       else -> UiText.DynamicText("Older")
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
            date = it.key,
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