package nl.codingwithlinda.core.test_data

import nl.codingwithlinda.core.domain.currency.CurrencySymbolProvider
import nl.codingwithlinda.core.domain.model.Currency
import nl.codingwithlinda.core.domain.model.Preferences

class FakeCurrencySymbolProvider: CurrencySymbolProvider {
    override fun getCurrencySymbol(prefs: Preferences): String {
        return when(prefs.currency){
            Currency.EURO -> "EUR"
            Currency.DOLLAR -> "USD"
            Currency.POUND -> "GBP"
            Currency.YEN -> "YEN"
            Currency.SWISS_FRANC -> "CHF"
        }
    }
}