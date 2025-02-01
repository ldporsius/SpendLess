package nl.codingwithlinda.spendless.di

import nl.codingwithlinda.core.domain.CurrencyFormatter

interface AppModule {

    val currencyFormatter: CurrencyFormatter
}