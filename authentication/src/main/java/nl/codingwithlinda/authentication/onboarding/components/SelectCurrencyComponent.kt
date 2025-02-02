package nl.codingwithlinda.authentication.onboarding.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.PopupProperties
import nl.codingwithlinda.core.domain.model.Currency
import nl.codingwithlinda.core.presentation.components.verticalScrollBar
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

    val scr = rememberScrollState()
    Column (
        modifier = modifier
            .fillMaxWidth()
            .height(200.dp)
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