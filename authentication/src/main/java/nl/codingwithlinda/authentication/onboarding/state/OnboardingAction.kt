package nl.codingwithlinda.authentication.onboarding.state

import nl.codingwithlinda.core.domain.model.ExpensesFormat

sealed interface OnboardingAction {
    data class OnSelectExpensesFormat(val expensesFormat: ExpensesFormat): OnboardingAction
}