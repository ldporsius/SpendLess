package nl.codingwithlinda.core.domain.model

data class Preferences(
    val expensesFormat: ExpensesFormat,
    val currency: Currency,
    val thousandsSeparator: Separator,
    val decimalSeparator: Separator,
    val decimalPlaces: Int
)
