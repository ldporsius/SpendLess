package nl.codingwithlinda.user_settings.preferences.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.dp
import nl.codingwithlinda.core.domain.model.Currency
import nl.codingwithlinda.core_ui.shared_components.verticalScrollBar
import nl.codingwithlinda.core_ui.currency.CurrencyUi

@Composable
fun SelectCurrencyComponent(
    modifier: Modifier = Modifier,
    currencies: List<CurrencyUi>,
    selectedCurrency: Int,
    onCurrencySelected: (Currency) -> Unit,
    expanded: Boolean,
    onDismissRequest: () -> Unit
) {

    val scr = rememberScrollState()
    Column (
        modifier = modifier
            .fillMaxWidth()
            .shadow(elevation = 8.dp)
            .height(200.dp)
            .background(color = MaterialTheme.colorScheme.surfaceContainerLowest,
                shape = MaterialTheme.shapes.medium
            )

            .verticalScroll(
            state = scr
        ).verticalScrollBar(
            scrollState = scr,
            viewPortHeight = 200f,
            color = MaterialTheme.colorScheme.primary

        )
        ,

    ) {

        currencies.onEachIndexed { index, currency ->

        DropdownMenuItem(
            text = {
                Text(text = currency.text)
            },
            onClick = {
                onCurrencySelected(Currency.entries[index])
            },
            trailingIcon = {
                if (index == selectedCurrency) {
                    Text(text = "✓")
                }
            }
        )
        }
    }
}