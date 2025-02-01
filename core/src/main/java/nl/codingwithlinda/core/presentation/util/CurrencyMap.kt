package nl.codingwithlinda.core.presentation.util

import nl.codingwithlinda.core.R
import nl.codingwithlinda.core.domain.model.Currency

val currencySymbolMap = mapOf(
    Currency.EURO to R.string.euro_symbol,
    Currency.DOLLAR to R.string.dollar_symbol,
    Currency.POUND to R.string.gbpound_symbol,
    Currency.YEN to R.string.yen_symbol,
    Currency.SWISS_FRANC to R.string.swissfranc_symbol
)

val currencyToUiText = mapOf(
    Currency.EURO to UiText.DynamicText("Euro (EUR)"),
    Currency.DOLLAR to UiText.DynamicText("US Dollar (USD)"),
    Currency.POUND to UiText.DynamicText("British Pound Sterling (GBP)"),
    Currency.YEN to UiText.DynamicText("Japanese Yen (JPY)"),
    Currency.SWISS_FRANC to UiText.DynamicText("Swiss Franc (CHF)")
)