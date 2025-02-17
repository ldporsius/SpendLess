package nl.codingwithlinda.dashboard.transactions.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import nl.codingwithlinda.core.test_data.fakeTransactions
import nl.codingwithlinda.core_ui.SpendLessTheme
import nl.codingwithlinda.core_ui.primaryFixed
import nl.codingwithlinda.dashboard.transactions.presentation.ui_model.TransactionUi
import nl.codingwithlinda.dashboard.transactions.presentation.ui_model.mapping.toUi

@Composable
fun TransactionsComponent(
    modifier: Modifier = Modifier,
    transactions: List<TransactionUi>
) {

    val context = LocalContext.current
    Box(modifier = Modifier
        .fillMaxWidth()
        .height(480.dp)
        .background(
            color = MaterialTheme.colorScheme.surface,
            shape = RoundedCornerShape(
                topStart = 32.dp,
                topEnd = 32.dp
            )
        )
        .padding(16.dp)
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = androidx.compose.foundation.layout.Arrangement.spacedBy(8.dp)
        ) {
            items(transactions) { transaction ->
                Row {
                    Box(
                        modifier = Modifier

                            .background(
                                color = primaryFixed,
                                shape = MaterialTheme.shapes.small
                            ).padding(8.dp)
                    ) {
                        Text(transaction.imageText(context))
                    }

                    Text(transaction.title)
                }

            }
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