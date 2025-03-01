package nl.codingwithlinda.core.test_data.test_data_generators

import nl.codingwithlinda.core.domain.model.Currency
import nl.codingwithlinda.core.domain.model.ExpensesFormat
import nl.codingwithlinda.core.domain.model.Preferences
import nl.codingwithlinda.core.domain.model.PreferencesAccount
import nl.codingwithlinda.core.domain.model.Separator

fun fakePreferences() = Preferences(
    expensesFormat = ExpensesFormat.MINUS,
    currency = Currency.YEN,
    thousandsSeparator = Separator.PERIOD,
    decimalSeparator = Separator.COMMA,
    decimalPlaces = 2
)

fun fakePreferencesAccount(
    accountId: String
): PreferencesAccount {
    return PreferencesAccount(
        preferences = fakePreferences(),
        accountId = accountId
    )
}