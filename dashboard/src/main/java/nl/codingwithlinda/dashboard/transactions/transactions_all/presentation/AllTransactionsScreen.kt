package nl.codingwithlinda.dashboard.transactions.transactions_all.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import nl.codingwithlinda.core_ui.secondaryFixed
import nl.codingwithlinda.dashboard.transactions.common.components.TransactionItem
import nl.codingwithlinda.dashboard.transactions.common.ui_model.TransactionGroupUi


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AllTransactionsScreen(
    transactions: List<TransactionGroupUi>,
    onNavBack: () -> Unit
) {

    Scaffold(
        topBar = {
            androidx.compose.material3.TopAppBar(
                title = {
                    Text(text = "All Transactions")
                },
                navigationIcon = {
                    androidx.compose.material3.IconButton(
                        onClick = {
                            onNavBack()
                        }
                    ){
                        androidx.compose.material3.Icon(
                            imageVector = androidx.compose.material.icons.Icons.Default.ArrowBack, contentDescription = null)
                    }
                }
            )
        },
        floatingActionButton = {
            androidx.compose.material3.FloatingActionButton(
                onClick = {
                    //new transaction
                },
                containerColor = secondaryFixed
            ){
                androidx.compose.material3.Icon(
                    imageVector = androidx.compose.material.icons.Icons.Default.Add, contentDescription = null)
            }
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {

            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = androidx.compose.foundation.layout.Arrangement.spacedBy(16.dp),
                contentPadding = PaddingValues(16.dp)
            ) {
                items(transactions) { transactionGroup ->
                    Column {
                        Text(text = transactionGroup.header.asString().uppercase(),
                            style = androidx.compose.material3.MaterialTheme.typography.titleMedium
                        )

                        transactionGroup.transactions.forEach { transaction ->
                            TransactionItem(
                                context = LocalContext.current,
                                transaction = transaction,
                                onClick = {},
                                modifier = Modifier.padding(vertical = 4.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}