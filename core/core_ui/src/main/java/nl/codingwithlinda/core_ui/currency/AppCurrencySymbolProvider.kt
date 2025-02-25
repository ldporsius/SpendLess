package nl.codingwithlinda.core_ui.currency

import android.content.Context
import nl.codingwithlinda.core.domain.model.Preferences

class AppCurrencySymbolProvider(
    private val context: Context
): CurrencySymbolProvider {
    override fun getCurrencySymbol(prefs: Preferences): String {
        val currencySymbol = currencySymbolMap[prefs.currency] ?: return ""
        return context.getString(currencySymbol)
    }
}