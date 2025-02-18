package nl.codingwithlinda.dashboard.transactions.presentation.components

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import nl.codingwithlinda.core_ui.primaryFixed
import nl.codingwithlinda.dashboard.transactions.presentation.ui_model.TransactionUi

@Composable
fun TransactionItem(
    context: Context,
    transaction: TransactionUi,
    imageSize: TextUnit = 24.sp,
    onClick: (id: String) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        verticalAlignment = androidx.compose.ui.Alignment.CenterVertically,
        horizontalArrangement = androidx.compose.foundation.layout.Arrangement.spacedBy(16.dp)
    ) {
        Box(
            modifier =
            TransactionItemModifier()
                .clickable {
                    onClick(transaction.id)
                }
        ) {
            Text(transaction.imageText(context),
                fontSize = imageSize
            )
        }

        Column {
            Text(transaction.title)
            Text(transaction.expenseLabel(context).asString(),
                style = MaterialTheme.typography.labelSmall)
        }

        Spacer(modifier = Modifier.weight(1f))
        Text(transaction.amount,
            style = MaterialTheme.typography.bodyLarge
        )
    }
}