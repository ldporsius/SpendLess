package nl.codingwithlinda.dashboard.transactions.transaction_create.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.focusable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SegmentedButton
import androidx.compose.material3.SingleChoiceSegmentedButtonRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import nl.codingwithlinda.core.domain.model.TransactionType
import nl.codingwithlinda.core_ui.LocalSegmentedButtonColorProvider
import nl.codingwithlinda.core_ui.LocalShapeProvider
import nl.codingwithlinda.core_ui.shared_components.TransparentTextField
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
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        SingleChoiceSegmentedButtonRow(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = MaterialTheme.colorScheme.surfaceContainerHigh,
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

        TransparentTextField(
            modifier = Modifier.fillMaxWidth(),
            text = uiState.recipient,
            onValueChange = {
                onAction(CreateTransactionAction.EnterRecipient(it))
            },
            placeholder = {
                Text(text = "Receiver",
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
            },
            isSingleLine = true,
            keyboardType = KeyboardType.Unspecified,
            keyboardActions = KeyboardActions(),
            imeAction = ImeAction.Next
        )

        Spacer(modifier = Modifier.height(16.dp))


       BasicTextField(
           modifier = Modifier
               .fillMaxWidth()
               .background(color = Color.Transparent)
               .padding(16.dp),
           value = uiState.amountEntered,
           onValueChange = {
               onAction(CreateTransactionAction.EnterAmount(it))
           },
           keyboardOptions = KeyboardOptions(
               keyboardType = KeyboardType.Decimal,
               imeAction = ImeAction.Done
           ),
           singleLine = true,
           cursorBrush = SolidColor(Color.Red)

       ){
           innerTextField ->
           Text(uiState.amount,
               style = MaterialTheme.typography.headlineLarge,
               textAlign = TextAlign.Center,
               modifier = Modifier.fillMaxWidth()
           )
       }



       /* CompositionLocalProvider(
            LocalContentColor provides Color.White
        ) {
            val amountTextFieldState = rememberTextFieldState()
            BasicTextField(
                state = amountTextFieldState,
                textStyle = TextStyle(
                    color = LocalContentColor.current,
                ),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = Color.DarkGray)
                    .padding(16.dp),

                )
        }*/
    }
}