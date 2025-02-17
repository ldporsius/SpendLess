package nl.codingwithlinda.dashboard.transactions.presentation

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import nl.codingwithlinda.core.test_data.fakeTransactions
import nl.codingwithlinda.core_ui.SpendLessTheme
import nl.codingwithlinda.dashboard.transactions.presentation.ui_model.TransactionUi
import nl.codingwithlinda.dashboard.transactions.presentation.ui_model.mapping.toUi

@Composable
fun TransactionsComponent(
    modifier: Modifier = Modifier,
    transactions: List<TransactionUi>
) {

    LazyColumn(
        modifier = modifier
    ) {
        items(transactions){transaction ->
            Text(transaction.title)
        }
    }
}

@Preview
@Composable
private fun TransactionsComponentPreview() {
    SpendLessTheme {
        TransactionsComponent(
            modifier = Modifier.fillMaxWidth(),
            transactions = fakeTransactions().map {
                it.toUi()
            }
        )
    }
}