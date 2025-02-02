package nl.codingwithlinda.authentication.onboarding.preview

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import nl.codingwithlinda.authentication.onboarding.OnboardingScreen
import nl.codingwithlinda.authentication.onboarding.state.OnboardingUiState
import nl.codingwithlinda.core.domain.model.Account
import nl.codingwithlinda.core.domain.model.Currency
import nl.codingwithlinda.core.domain.model.ExpensesFormat
import nl.codingwithlinda.core.domain.model.Preferences
import nl.codingwithlinda.core.domain.model.Separator
import nl.codingwithlinda.core_ui.SpendLessTheme

@Preview
@Composable
private fun OnboardingScreenPreview() {
    val preferences = Preferences(
        expensesFormat = ExpensesFormat.MINUS,
        currency = Currency.EURO,
        thousandsSeparator = Separator.PERIOD,
        decimalSeparator = Separator.COMMA,
        decimalPlaces = 2
    )
    SpendLessTheme {
        OnboardingScreen(
            uiState = OnboardingUiState(
                exampleFormattedText = "10.382,45",
                preferences = preferences,
                account = Account("Linda", "")
            ),
            onAction = {},
            onNavigate = {}
        )
    }
}