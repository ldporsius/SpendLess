package nl.codingwithlinda.core.domain.currency_format

import nl.codingwithlinda.core.domain.model.Preferences

interface CurrencyFormatter {

    fun formatCurrencyString(currency:String, preferences: Preferences): String
}