package nl.codingwithlinda.authentication.onboarding

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import nl.codingwithlinda.authentication.onboarding.state.OnboardingAction
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

    private val _preferences = MutableStateFlow(preferences)
    private val _uiState = MutableStateFlow(
        OnboardingUiState(
            exampleFormattedText,
            _preferences.value
        )
    )
    val uiState = _uiState.combine(_preferences){state, preferences ->
        state.copy(
            exampleFormattedText =  currencyFormatter.formatCurrencyString(exampleText, preferences),
            preferences = preferences
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), _uiState.value)

    fun handleAction(action: OnboardingAction) {
        when(action){
            is OnboardingAction.OnSelectExpensesFormat -> {
                _preferences.update {
                    it.copy(expensesFormat = action.expensesFormat)
                }
            }
        }
    }

}