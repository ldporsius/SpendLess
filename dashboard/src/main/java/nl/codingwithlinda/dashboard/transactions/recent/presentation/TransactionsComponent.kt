package nl.codingwithlinda.dashboard.transactions.recent.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import nl.codingwithlinda.core.test_data.fakePreferences
import nl.codingwithlinda.core.test_data.fakeTransactions
import nl.codingwithlinda.core_ui.SpendLessTheme
import nl.codingwithlinda.core_ui.currency.CurrencyFormatterFactory
import nl.codingwithlinda.dashboard.transactions.common.components.TransactionItem
import nl.codingwithlinda.dashboard.transactions.common.components.TransactionItemExpanded
import nl.codingwithlinda.dashboard.transactions.common.ui_model.TransactionGroupUi
import nl.codingwithlinda.dashboard.transactions.common.ui_model.mapping.groupByDate
import nl.codingwithlinda.dashboard.transactions.common.ui_model.mapping.toUi

@Composable
fun TransactionsComponent(
    modifier: Modifier = Modifier,
    transactions: List<TransactionGroupUi>,
    onShowAll: () -> Unit
) {

    var expandedId by remember {
        mutableStateOf("")
    }
    val context = LocalContext.current
    CompositionLocalProvider(LocalContentColor provides MaterialTheme.colorScheme.onSurface) {
        Box(
            modifier = Modifier
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
            Column(
                Modifier.fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth()
                        .padding(bottom = 8.dp),
                    horizontalArrangement = androidx.compose.foundation.layout.Arrangement.SpaceBetween
                ) {
                    Text(
                        "Latest transactions",
                        style = MaterialTheme.typography.titleMedium
                    )
                    Text(
                        "See all",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.padding(end = 8.dp)
                            .clickable {
                                onShowAll()
                            }
                    )
                }

                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = androidx.compose.foundation.layout.Arrangement.spacedBy(8.dp)
                ) {

                    items(transactions) { transactionGroup ->

                        Column(
                            verticalArrangement = Arrangement.spacedBy(4.dp)
                        ) {
                            Text(
                                transactionGroup.header.asString().uppercase(),
                                style = MaterialTheme.typography.bodySmall
                            )
                            transactionGroup.transactions.onEach { transaction ->
                                if (transaction.id == expandedId) {
                                    TransactionItemExpanded(
                                        context = context,
                                        transaction = transaction
                                    )
                                } else {
                                    TransactionItem(
                                        context = context,
                                        transaction = transaction,
                                        onClick = {
                                            expandedId = it
                                        }
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

