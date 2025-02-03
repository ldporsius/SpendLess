package nl.codingwithlinda.authentication.onboarding

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import nl.codingwithlinda.authentication.onboarding.state.OnboardingAction
import nl.codingwithlinda.authentication.onboarding.state.OnboardingUiState
import nl.codingwithlinda.core.domain.CurrencyFormatter
import nl.codingwithlinda.core.domain.local_cache.DataSourceAccess
import nl.codingwithlinda.core.domain.model.Account
import nl.codingwithlinda.core.domain.model.Currency
import nl.codingwithlinda.core.domain.model.ExpensesFormat
import nl.codingwithlinda.core.domain.model.Preferences
import nl.codingwithlinda.core.domain.model.Separator

class OnboardingViewModel(
    private val currencyFormatter: CurrencyFormatter,
    private val account: Account,
    private val accountAccess: DataSourceAccess<Account, Pair<String, String>>,
    private val preferencesAccess: DataSourceAccess<Preferences, Long>,
    private val navToDashboard: () -> Unit
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
            _preferences.value,
            account
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
                    preferencesAccess.create(_preferences.value)
                    accountAccess.create(account)
                    navToDashboard()
                }
            }
        }
    }

}