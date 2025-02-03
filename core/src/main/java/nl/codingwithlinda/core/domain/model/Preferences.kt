package nl.codingwithlinda.core.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class Preferences(
    val expensesFormat: ExpensesFormat,
    val currency: Currency,
    val thousandsSeparator: Separator,
    val decimalSeparator: Separator,
    val decimalPlaces: Int
)
