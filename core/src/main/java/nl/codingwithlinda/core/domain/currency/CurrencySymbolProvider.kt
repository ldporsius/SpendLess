package nl.codingwithlinda.core.domain.currency

import nl.codingwithlinda.core.domain.model.Preferences

interface CurrencySymbolProvider {

    fun getCurrencySymbol(prefs: Preferences): String
}