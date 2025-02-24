package nl.codingwithlinda.user_settings.onboarding.presentation.preview

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import nl.codingwithlinda.user_settings.onboarding.presentation.OnboardingScreen
import nl.codingwithlinda.core.domain.model.Currency
import nl.codingwithlinda.core.domain.model.ExpensesFormat
import nl.codingwithlinda.core.domain.model.Preferences
import nl.codingwithlinda.core.domain.model.Separator
import nl.codingwithlinda.core_ui.SpendLessTheme
import nl.codingwithlinda.user_settings.preferences.presentation.state.UserPrefsUiState

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
            uiState = UserPrefsUiState(
                exampleFormattedText = "10.382,45",
                preferences = preferences,

                ),
            onAction = {},
            onNavigate = {}
        )
    }
}