package nl.codingwithlinda.dashboard.transactions.transactions_all.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import nl.codingwithlinda.dashboard.transactions.common.components.TransactionItem
import nl.codingwithlinda.dashboard.transactions.common.ui_model.TransactionGroupUi

@Composable
fun AllTransactionsScreen(
    transactions: List<TransactionGroupUi>
) {
    LazyColumn {
        items(transactions){transactionGroup ->
            Column {
                Text(text = transactionGroup.header.asString())

                transactionGroup.transactions.forEach {transaction ->
                    TransactionItem(
                        context = LocalContext.current,
                        transaction = transaction,
                        onClick = {}
                    )
                }
            }
        }
    }
}