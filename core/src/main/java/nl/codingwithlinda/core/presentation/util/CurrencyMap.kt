package nl.codingwithlinda.core.presentation.util

import nl.codingwithlinda.core.domain.model.Currency
import nl.codingwithlinda.core_ui.util.UiText
import nl.codingwithlinda.core_ui.R as uiR

val currencySymbolMap = mapOf(
    Currency.EURO to uiR.string.euro_symbol,
    Currency.DOLLAR to uiR.string.dollar_symbol,
    Currency.POUND to uiR.string.gbpound_symbol,
    Currency.YEN to uiR.string.yen_symbol,
    Currency.SWISS_FRANC to uiR.string.swissfranc_symbol
)

val currencyToUiText = mapOf(
    Currency.EURO to UiText.DynamicText("Euro (EUR)"),
    Currency.DOLLAR to UiText.DynamicText("US Dollar (USD)"),
    Currency.POUND to UiText.DynamicText("British Pound Sterling (GBP)"),
    Currency.YEN to UiText.DynamicText("Japanese Yen (JPY)"),
    Currency.SWISS_FRANC to UiText.DynamicText("Swiss Franc (CHF)")
)