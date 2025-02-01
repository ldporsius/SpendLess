package nl.codingwithlinda.authentication.onboarding.state

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import nl.codingwithlinda.core.domain.model.Currency
import nl.codingwithlinda.core.domain.model.Preferences
import nl.codingwithlinda.core.presentation.util.CurrencyUi
import nl.codingwithlinda.core.presentation.util.currencySymbolMap
import nl.codingwithlinda.core.presentation.util.currencyToUiText

data class OnboardingUiState(
    val exampleFormattedText: String,
    val preferences: Preferences
){
    @Composable
    fun CurrencyUi(): List<CurrencyUi> {
        val context = LocalContext.current
        val texts = Currency.values().map {
            val symbol = currencySymbolMap[it]?.let {
                context.getString(it)
            } ?: ""
            val text = currencyToUiText[it]?.asString() ?: ""
            CurrencyUi(
                text = symbol + " " + text
            )
        }

        return texts
    }
}


