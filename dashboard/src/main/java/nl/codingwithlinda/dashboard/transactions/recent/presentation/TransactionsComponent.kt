package nl.codingwithlinda.dashboard.transactions.recent.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import nl.codingwithlinda.dashboard.transactions.common.components.TransactionsList
import nl.codingwithlinda.dashboard.transactions.common.ui_model.TransactionGroupUi

@Composable
fun TransactionsComponent(
    modifier: Modifier = Modifier,
    transactions: List<TransactionGroupUi>,
    onShowAll: () -> Unit
) {


    CompositionLocalProvider(LocalContentColor provides MaterialTheme.colorScheme.onSurface) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(480.dp)
                .background(
                    color = MaterialTheme.colorScheme.surfaceContainerLowest,
                    shape = RoundedCornerShape(
                        topStart = 32.dp,
                        topEnd = 32.dp
                    )
                )
                .padding(top = 16.dp, start = 16.dp, end = 16.dp)
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

                TransactionsList(
                    transactions = transactions
                )
            }
        }
    }
}

