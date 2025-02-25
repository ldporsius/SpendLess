package nl.codingwithlinda.user_settings.preferences.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import nl.codingwithlinda.core.domain.model.Currency
import nl.codingwithlinda.core.domain.model.ExpensesFormat
import nl.codingwithlinda.core.domain.model.Preferences
import nl.codingwithlinda.core.domain.model.Separator
import nl.codingwithlinda.core.domain.result.SpendResult
import nl.codingwithlinda.core_ui.currency.formatters.CurrencyFormatter
import nl.codingwithlinda.user_settings.main.presentation.state.UserPrefsAction
import nl.codingwithlinda.user_settings.preferences.domain.IGetUserPrefs
import nl.codingwithlinda.user_settings.preferences.presentation.state.UserPrefsUiState

class UserPreferencesViewModel(
    private val currencyFormatter: CurrencyFormatter,
    private val getUserPreferencesUseCase: IGetUserPrefs,
    private val actionOnSave: (prefs: Preferences) -> Unit
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
        UserPrefsUiState(
            exampleFormattedText.text,
            _preferences.value,
        )
    )
    val uiState = _uiState.combine(_preferences){state, preferences ->
        state.copy(
            exampleFormattedText =  currencyFormatter.formatCurrencyString(exampleText, preferences).text,
            preferences = preferences
        )
    }.onStart {
        val prefs = getUserPreferencesUseCase.getPreferencesForLoggedInUser().let {res ->
            when(res){
                is SpendResult.Failure -> examplePrefs
                is SpendResult.Success -> res.data.preferences
            }
        }
        _preferences.update {
            prefs
        }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), _uiState.value)

    fun handleAction(action: UserPrefsAction) {
        when(action){
            is UserPrefsAction.OnSelectExpensesFormat -> {
                _preferences.update {
                    it.copy(expensesFormat = action.expensesFormat)
                }
            }

            is UserPrefsAction.OnSelectCurrency -> {
                _preferences.update {
                    it.copy(currency = action.currency)
                }
            }

            is UserPrefsAction.OnSelectDecimalSeparator -> {
                _preferences.update {
                    it.copy(decimalSeparator = action.separator)
                }
            }
            is UserPrefsAction.OnSelectThousandsSeparator -> {
                _preferences.update {
                    it.copy(thousandsSeparator = action.separator)
                }
            }

            UserPrefsAction.SavePrefs -> {
                viewModelScope.launch {
                    actionOnSave(_preferences.value)
                }
            }
        }
    }

}