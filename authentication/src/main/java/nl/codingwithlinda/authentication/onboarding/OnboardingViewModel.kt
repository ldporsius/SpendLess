package nl.codingwithlinda.authentication.onboarding

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import nl.codingwithlinda.authentication.onboarding.state.OnboardingUiState
import nl.codingwithlinda.core.domain.CurrencyFormatter
import nl.codingwithlinda.core.domain.model.Currency
import nl.codingwithlinda.core.domain.model.ExpensesFormat
import nl.codingwithlinda.core.domain.model.Preferences
import nl.codingwithlinda.core.domain.model.Separator

class OnboardingViewModel(
    private val currencyFormatter: CurrencyFormatter
): ViewModel() {

    private val exampleText = "1038245"
    private val preferences = Preferences(
        expensesFormat = ExpensesFormat.MINUS,
        currency = Currency.EURO,
        thousandsSeparator = Separator.PERIOD,
        decimalSeparator = Separator.COMMA,
        decimalPlaces = 2
    )
    private val exampleFormattedText =
        currencyFormatter.formatCurrencyString(exampleText, preferences)

    private val _uiState = MutableStateFlow(
        OnboardingUiState(exampleFormattedText)
    )
    val uiState = _uiState.asStateFlow()


}