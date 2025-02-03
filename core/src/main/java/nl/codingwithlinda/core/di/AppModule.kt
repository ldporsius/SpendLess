package nl.codingwithlinda.core.di

import nl.codingwithlinda.core.domain.CurrencyFormatter
import nl.codingwithlinda.core.domain.local_cache.DataSourceAccess
import nl.codingwithlinda.core.domain.model.Account
import nl.codingwithlinda.core.domain.model.Preferences

interface AppModule {

    val currencyFormatter: CurrencyFormatter

    val accountAccess: DataSourceAccess<Account, Pair<String, String>>
    val preferencesAccess: DataSourceAccess<Preferences, Long>
}