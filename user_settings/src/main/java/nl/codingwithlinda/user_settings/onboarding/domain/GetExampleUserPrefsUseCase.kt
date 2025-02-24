package nl.codingwithlinda.user_settings.onboarding.domain

import nl.codingwithlinda.core.domain.error.RootError
import nl.codingwithlinda.core.domain.model.Currency
import nl.codingwithlinda.core.domain.model.ExpensesFormat
import nl.codingwithlinda.core.domain.model.Preferences
import nl.codingwithlinda.core.domain.model.PreferencesAccount
import nl.codingwithlinda.core.domain.model.Separator
import nl.codingwithlinda.core.domain.result.SpendResult
import nl.codingwithlinda.user_settings.preferences.domain.IGetUserPrefs

class GetExampleUserPrefsUseCase: IGetUserPrefs {
    override suspend fun getPreferencesForLoggedInUser(): SpendResult<PreferencesAccount, RootError> {
        return SpendResult.Success(
            PreferencesAccount(
                preferences = Preferences(
                    expensesFormat = ExpensesFormat.MINUS,
                    currency = Currency.EURO,
                    thousandsSeparator = Separator.PERIOD,
                    decimalSeparator = Separator.COMMA,
                    decimalPlaces = 2
                ),
                accountId = ""
            )
        )
    }
}