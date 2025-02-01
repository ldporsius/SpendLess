package nl.codingwithlinda.authentication.onboarding.components

import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import nl.codingwithlinda.core.domain.model.Currency
import nl.codingwithlinda.core.presentation.util.CurrencyUi

@Composable
fun SelectCurrencyComponent(
    modifier: Modifier = Modifier,
    currencies: List<CurrencyUi>,
    selectedCurrency: Int,
    onCurrencySelected: (Currency) -> Unit,
    expanded: Boolean,
    onDismissRequest: () -> Unit
) {

    DropdownMenu(
        expanded = expanded,
        onDismissRequest = onDismissRequest
    ) {
        currencies.onEachIndexed { index, currency ->

        DropdownMenuItem(
            text = {
                Text(text = currency.text)
            },
            onClick = {
                onCurrencySelected(Currency.values()[index])
            }
        )
        }
    }
}