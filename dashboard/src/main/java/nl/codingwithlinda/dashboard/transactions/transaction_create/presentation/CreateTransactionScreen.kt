package nl.codingwithlinda.dashboard.transactions.transaction_create.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SegmentedButton
import androidx.compose.material3.SingleChoiceSegmentedButtonRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import nl.codingwithlinda.core.domain.model.TransactionType
import nl.codingwithlinda.core_ui.LocalSegmentedButtonColorProvider
import nl.codingwithlinda.core_ui.LocalShapeProvider
import nl.codingwithlinda.core_ui.shared_components.TransparentTextField
import nl.codingwithlinda.dashboard.transactions.transaction_create.presentation.state.CreateTransactionAction
import nl.codingwithlinda.dashboard.transactions.transaction_create.presentation.state.CreateTransactionUiState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateTransactionScreen(
    modifier: Modifier = Modifier,
    uiState: CreateTransactionUiState = CreateTransactionUiState(),
    onAction: (CreateTransactionAction) -> Unit,
    categoryPicker: @Composable () -> Unit
) {

    Column(
        modifier = modifier
            .imePadding()
            .verticalScroll(rememberScrollState())
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

        val recipientFocusRequester = remember { FocusRequester() }
        val keyboardController = LocalSoftwareKeyboardController.current
        var hasFocus by  remember { mutableStateOf(false) }
        TransparentTextField(
            modifier = Modifier
                .fillMaxWidth()
                .onFocusChanged {
                    hasFocus = it.hasFocus
                }
            ,
            text = uiState.recipient,
            onValueChange = {
                onAction(CreateTransactionAction.EnterRecipient(it))
            },
            placeholder = {
               if (!hasFocus) {
                   Text(
                       text = uiState.recipientPlaceholder(),
                       textAlign = TextAlign.Center,
                       modifier = Modifier.fillMaxWidth()
                   )
               }
            },
            isSingleLine = true,
            keyboardType = KeyboardType.Text,
            keyboardActions = KeyboardActions(),
            imeAction = ImeAction.Next
        )

        Spacer(modifier = Modifier.height(16.dp))

//amount
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

        //note
        var hasNoteFocus by remember { mutableStateOf(false) }
        Box(modifier = Modifier
            .fillMaxWidth(),
            contentAlignment = Alignment.Center

        ) {
            BasicTextField(
                value = TextFieldValue(
                    text = uiState.description,
                    selection = TextRange(uiState.descriptionText().length)
                ),
                onValueChange = {

                    onAction(CreateTransactionAction.EnterDescription(it.text))

                },
                modifier = Modifier
                    .fillMaxWidth()
                    .onFocusChanged {
                        hasNoteFocus = it.hasFocus
                    }

                    .padding(16.dp),
                textStyle = TextStyle(
                    textAlign = TextAlign.Center
                ),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Done

                )
            )
            if (uiState.description.isEmpty() && !hasNoteFocus) {
                Text(
                    text = uiState.descriptionText(),
                    style = MaterialTheme.typography.bodyMedium,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }

        //dropdown category
        categoryPicker()

        //save button
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                onAction(CreateTransactionAction.SaveTransaction)
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Create")
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