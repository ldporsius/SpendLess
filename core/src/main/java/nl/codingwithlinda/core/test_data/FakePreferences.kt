package nl.codingwithlinda.core.test_data

import nl.codingwithlinda.core.domain.model.Currency
import nl.codingwithlinda.core.domain.model.ExpensesFormat
import nl.codingwithlinda.core.domain.model.Preferences
import nl.codingwithlinda.core.domain.model.Separator

fun fakePreferences() = Preferences(
    expensesFormat = ExpensesFormat.MINUS,
    currency = Currency.YEN,
    thousandsSeparator = Separator.PERIOD,
    decimalSeparator = Separator.COMMA,
    decimalPlaces = 2
)
