package nl.codingwithlinda.core.di

import nl.codingwithlinda.core.domain.CurrencyFormatter

interface AppModule {

    val currencyFormatter: CurrencyFormatter

}