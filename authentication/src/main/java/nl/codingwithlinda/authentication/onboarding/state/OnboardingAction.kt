package nl.codingwithlinda.authentication.onboarding.state

import nl.codingwithlinda.core.domain.model.Currency
import nl.codingwithlinda.core.domain.model.ExpensesFormat
import nl.codingwithlinda.core.domain.model.Separator

sealed interface OnboardingAction {
    data class OnSelectExpensesFormat(val expensesFormat: ExpensesFormat): OnboardingAction
    data class OnSelectCurrency(val currency: Currency): OnboardingAction
    data class OnSelectDecimalSeparator(val separator: Separator) : OnboardingAction
    data class OnSelectThousandsSeparator(val separator: Separator) : OnboardingAction
}