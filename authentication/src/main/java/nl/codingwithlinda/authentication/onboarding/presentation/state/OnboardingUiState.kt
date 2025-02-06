package nl.codingwithlinda.authentication.onboarding.presentation.state

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import nl.codingwithlinda.core.domain.model.Account
import nl.codingwithlinda.core.domain.model.Currency
import nl.codingwithlinda.core.domain.model.Preferences
import nl.codingwithlinda.core.presentation.util.CurrencyUi
import nl.codingwithlinda.core.presentation.util.currencySymbolMap
import nl.codingwithlinda.core.presentation.util.currencyToUiText

data class OnboardingUiState(
    val exampleFormattedText: String,
    val preferences: Preferences,
    val account: Account
){
    @Composable
    fun currencyUiList(): List<CurrencyUi> {
        val texts = Currency.entries.map {
           getCurrencyUi(it)
        }
        return texts
    }

    @Composable
    fun getCurrencyUi(currency: Currency): CurrencyUi{
        val context = LocalContext.current

        val symbol = currencySymbolMap[currency]?.let {
            context.getString(it)
        } ?: ""
        val text = currencyToUiText[currency]?.asString() ?: ""
        return CurrencyUi(
            text = symbol + " " + text
        )
    }

    fun isStartTrackingEnabled(): Boolean{
        return preferences.thousandsSeparator != preferences.decimalSeparator
    }
}


