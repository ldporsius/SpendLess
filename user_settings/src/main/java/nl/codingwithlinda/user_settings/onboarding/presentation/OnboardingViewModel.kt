package nl.codingwithlinda.user_settings.onboarding.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import nl.codingwithlinda.core_ui.currency.CurrencyFormatter
import nl.codingwithlinda.core.domain.model.Account
import nl.codingwithlinda.core.domain.model.Currency
import nl.codingwithlinda.core.domain.model.ExpensesFormat
import nl.codingwithlinda.core.domain.model.Preferences
import nl.codingwithlinda.core.domain.model.Separator
import nl.codingwithlinda.user_settings.onboarding.domain.SaveAccountAndPreferencesUseCase
import nl.codingwithlinda.user_settings.onboarding.presentation.state.OnboardingAction
import nl.codingwithlinda.user_settings.onboarding.presentation.state.OnboardingUiState

class OnboardingViewModel(
    private val currencyFormatter: CurrencyFormatter,
    private val saveAccountAndPreferencesUseCase: SaveAccountAndPreferencesUseCase,
    private val account: Account,
    private val navToDashboard: () -> Unit
): ViewModel() {

    private val exampleText = "1038245"
    private val examplePrefs = Preferences(
        expensesFormat = ExpensesFormat.MINUS,
        currency = Currency.EURO,
        thousandsSeparator = Separator.PERIOD,
        decimalSeparator = Separator.COMMA,
        decimalPlaces = 2
    )
    private val exampleFormattedText =
        currencyFormatter.formatCurrencyString(exampleText, examplePrefs)

    private val _preferences = MutableStateFlow(examplePrefs)
    private val _uiState = MutableStateFlow(
        OnboardingUiState(
            exampleFormattedText,
            _preferences.value,
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

            is OnboardingAction.OnSelectCurrency -> {
                _preferences.update {
                    it.copy(currency = action.currency)
                }
            }

            is OnboardingAction.OnSelectDecimalSeparator -> {
                _preferences.update {
                    it.copy(decimalSeparator = action.separator)
                }
            }
            is OnboardingAction.OnSelectThousandsSeparator -> {
                _preferences.update {
                    it.copy(thousandsSeparator = action.separator)
                }
            }

            OnboardingAction.SaveOnboarding -> {
                viewModelScope.launch {
                    saveAccountAndPreferencesUseCase.save(account, _preferences.value)
                    navToDashboard()
                }
            }
        }
    }

}