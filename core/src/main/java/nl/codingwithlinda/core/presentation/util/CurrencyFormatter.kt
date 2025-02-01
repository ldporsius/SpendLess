package nl.codingwithlinda.core.presentation.util

import android.content.Context
import nl.codingwithlinda.core.domain.model.Preferences

class CurrencyFormatter(
    private val context: Context
) {

    fun formatCurrencyString(currency:String, preferences: Preferences): String {

        val currencySymbol = currencyMap[preferences.currency] ?: return currency
        val currencyString = context.getString(currencySymbol)


        return currency
    }
}