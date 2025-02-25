package nl.codingwithlinda.dashboard.transactions.transaction_create.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SegmentedButton
import androidx.compose.material3.SingleChoiceSegmentedButtonRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import nl.codingwithlinda.core.domain.model.TransactionType
import nl.codingwithlinda.core_ui.LocalSegmentedButtonColorProvider
import nl.codingwithlinda.core_ui.LocalShapeProvider
import nl.codingwithlinda.dashboard.R
import nl.codingwithlinda.dashboard.transactions.transaction_create.presentation.state.CreateTransactionAction
import nl.codingwithlinda.dashboard.transactions.transaction_create.presentation.state.CreateTransactionUiState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateTransactionScreen(
    modifier: Modifier = Modifier,
    uiState: CreateTransactionUiState = CreateTransactionUiState(),
    onAction: (CreateTransactionAction) -> Unit

) {

    Column(
        modifier = modifier
            .padding(16.dp)
    ) {
        SingleChoiceSegmentedButtonRow(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = MaterialTheme.colorScheme.surfaceContainerHigh,
                    shape = LocalShapeProvider.current.medium
                )
                .padding(horizontal = 4.dp)
        ) {
           SegmentedButton(
               selected = uiState.transactionType == TransactionType.EXPENSE,
               onClick = {
                   onAction(CreateTransactionAction.ToggleExpenseIncome)
               },
               shape = LocalShapeProvider.current.medium,
               colors = LocalSegmentedButtonColorProvider.current,
               icon = {
                   Icon(painter = painterResource(nl.codingwithlinda.core_ui.R.drawable.trending_down,),
                       contentDescription = null,
                   )
               }
           ) {
               Text(text = "Expense")
           }
            SegmentedButton(
                selected = uiState.transactionType == TransactionType.INCOME,
                onClick = {
                    onAction(CreateTransactionAction.ToggleExpenseIncome)
                },
                shape = LocalShapeProvider.current.small,
                colors = LocalSegmentedButtonColorProvider.current,
                icon = {
                    Icon(painter = painterResource(nl.codingwithlinda.core_ui.R.drawable.trending_up,),
                        contentDescription = null,
                    )
                }
            ) {
                Text(text = "Income")
            }
        }
    }
}