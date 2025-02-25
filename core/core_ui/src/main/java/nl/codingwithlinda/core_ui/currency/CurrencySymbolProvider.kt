package nl.codingwithlinda.core_ui.currency

import nl.codingwithlinda.core.domain.model.Preferences

interface CurrencySymbolProvider {

    fun getCurrencySymbol(prefs: Preferences): String
}