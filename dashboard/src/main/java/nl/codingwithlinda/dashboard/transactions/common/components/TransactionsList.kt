package nl.codingwithlinda.dashboard.transactions.common.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import nl.codingwithlinda.dashboard.transactions.common.ui_model.TransactionGroupUi

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TransactionsList(
    transactions: List<TransactionGroupUi>,
    modifier: Modifier = Modifier) {

    val context = LocalContext.current
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = androidx.compose.foundation.layout.Arrangement.spacedBy(8.dp)
    ) {

        transactions.onEach {transactionGroup ->
            stickyHeader{
                Text(
                    transactionGroup.header.asString().uppercase(),
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(color = MaterialTheme.colorScheme.surfaceContainerLowest)
                        .padding(vertical = 16.dp)
                )
            }
            items(transactionGroup.transactions){transaction ->
                if (transaction.description.isNotEmpty()) {
                    TransactionItemExpanded(
                        context = context,
                        transaction = transaction
                    )
                } else {
                    TransactionItem(
                        context = context,
                        transaction = transaction,
                        )
                }
            }

        }

    }
}