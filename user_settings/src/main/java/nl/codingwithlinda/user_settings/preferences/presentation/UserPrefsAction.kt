package nl.codingwithlinda.user_settings.preferences.presentation

import nl.codingwithlinda.core.domain.model.Currency
import nl.codingwithlinda.core.domain.model.ExpensesFormat
import nl.codingwithlinda.core.domain.model.Separator

sealed interface UserPrefsAction {
    data class OnSelectExpensesFormat(val expensesFormat: ExpensesFormat): UserPrefsAction
    data class OnSelectCurrency(val currency: Currency): UserPrefsAction
    data class OnSelectDecimalSeparator(val separator: Separator) : UserPrefsAction
    data class OnSelectThousandsSeparator(val separator: Separator) : UserPrefsAction

    data object SavePrefs: UserPrefsAction
}