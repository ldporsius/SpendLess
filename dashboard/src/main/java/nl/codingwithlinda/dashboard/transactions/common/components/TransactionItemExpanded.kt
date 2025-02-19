package nl.codingwithlinda.dashboard.transactions.common.components

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import nl.codingwithlinda.core_ui.SpendLessTheme
import nl.codingwithlinda.dashboard.transactions.common.ui_model.TransactionUi

@Composable
fun TransactionItemExpanded(
    context: Context,
    transaction: TransactionUi,
    imageSize: TextUnit = 24.sp,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .background(
                color = MaterialTheme.colorScheme.surfaceContainerLowest,
                shape = MaterialTheme.shapes.small
            ),
        verticalAlignment = Alignment.Top,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Box(
            modifier = Modifier
                .width(52.dp)
                .height(56.dp),

          contentAlignment = Alignment.TopStart
        ) {
            Box(
                modifier = TransactionItemModifier()
            ){
                Text(transaction.imageText(context),
                    fontSize = imageSize
                )
            }

            Box(
                modifier = Modifier
                    .size(24.dp)
                    .align(Alignment.BottomEnd)
                    .shadow(
                        elevation = 4.dp,
                        shape = MaterialTheme.shapes.small
                    )
                    .background(
                        color = MaterialTheme.colorScheme.surfaceContainerLowest,
                        shape = MaterialTheme.shapes.small
                    )
                ,
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = nl.codingwithlinda.core_ui.R.drawable.notes),
                    contentDescription = null,
                    modifier = Modifier
                        .size(16.dp)

                )
            }
        }

        Column {
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
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

            Text(transaction.description,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(top = 16.dp)
            )

        }
    }
}

