package nl.codingwithlinda.spendless.di

import android.app.Application
import nl.codingwithlinda.core.domain.CurrencyFormatter
import nl.codingwithlinda.core.presentation.util.CurrencyFormatterImpl

class AndroidAppModule(
    private val application: Application
): AppModule {
    override val currencyFormatter: CurrencyFormatter
        get() = CurrencyFormatterImpl(application)
}