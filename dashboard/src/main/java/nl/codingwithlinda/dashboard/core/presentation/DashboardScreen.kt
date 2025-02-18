package nl.codingwithlinda.dashboard.core.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import nl.codingwithlinda.core.domain.model.ExpenseCategory
import nl.codingwithlinda.core.test_data.fakePreferences
import nl.codingwithlinda.core.test_data.fakeTransactions
import nl.codingwithlinda.core_ui.SpendLessTheme
import nl.codingwithlinda.core_ui.currency.CurrencyFormatterExpense
import nl.codingwithlinda.core_ui.currency.CurrencyFormatterFactory
import nl.codingwithlinda.core_ui.dashboardBackground
import nl.codingwithlinda.core_ui.primaryFixed
import nl.codingwithlinda.core_ui.purplish
import nl.codingwithlinda.core_ui.secondaryFixed
import nl.codingwithlinda.dashboard.categories.presentation.mapping.expenseCategoriesToUi
import nl.codingwithlinda.dashboard.core.presentation.state.AccountUiState
import nl.codingwithlinda.dashboard.transactions.presentation.TransactionsComponent
import nl.codingwithlinda.dashboard.transactions.presentation.ui_model.mapping.groupByDate
import nl.codingwithlinda.dashboard.transactions.presentation.ui_model.mapping.toUi

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardScreen(
   accountUiState: AccountUiState,
   transactionsComponent: @Composable () -> Unit
) {
    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        containerColor = Color.Transparent,
        contentColor = MaterialTheme.colorScheme.onPrimary,
        topBar = {
            CompositionLocalProvider(LocalContentColor provides Color.White) {
                TopAppBar(
                    title = {
                        Text(accountUiState.userName,
                            color = LocalContentColor.current)
                    },
                    actions = {
                        IconButton(
                            onClick = { /*settings*/ },
                        ) {
                            Icon(
                                Icons.Default.Settings, contentDescription = "Settings",
                               )
                        }
                    },
                    colors = TopAppBarDefaults.topAppBarColors().copy(
                        containerColor = Color.Transparent,
                        titleContentColor = LocalContentColor.current,
                        actionIconContentColor = LocalContentColor.current
                    )
                )
            }
        }

    ) {padding ->
        Box(modifier = Modifier
            .fillMaxSize()
            .background(dashboardBackground)
            .padding(padding)

        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Column(
                    modifier = Modifier.fillMaxWidth()
                        .padding(top = 48.dp, bottom = 48.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        accountUiState.accountBalance,
                        style = MaterialTheme.typography.displayLarge
                    )
                    Text(
                        "Account Balance",
                        style = MaterialTheme.typography.bodySmall
                    )
                }

                val mostPopularCategoryUi = accountUiState.mostPopularCategory

                mostPopularCategoryUi?.let {
                Box(modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        color = purplish,
                        shape = MaterialTheme.shapes.medium
                    )
                    .padding(8.dp)
                ) {

                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                        ) {

                            Box(
                                modifier = Modifier
                                    .background(
                                        color = primaryFixed,
                                        shape = MaterialTheme.shapes.medium
                                    )
                                    .padding(12.dp)
                            ) {
                                Text(
                                    it.imageText,
                                    fontSize = 30.sp
                                )
                            }
                            Column(
                                modifier = Modifier.padding(16.dp)
                            ) {
                                Text(
                                    it.expenseLabel.asString(),
                                    style = MaterialTheme.typography.titleLarge
                                )
                                Text(
                                    "Most popular category",
                                    style = MaterialTheme.typography.bodySmall
                                )
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))

                CompositionLocalProvider(LocalContentColor provides MaterialTheme.colorScheme.onSurface) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(IntrinsicSize.Max),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Box(
                            modifier = Modifier.background(
                                color = primaryFixed,
                                shape = MaterialTheme.shapes.medium
                            )
                                .fillMaxHeight()
                                .weight(1f)
                        ) {
                            accountUiState.largestTransaction.let {
                                Column(
                                    modifier = Modifier.padding(16.dp)
                                ) {
                                    Row(
                                        modifier = Modifier.fillMaxWidth(),
                                        horizontalArrangement = Arrangement.SpaceBetween,
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Text(it.title,
                                            style = MaterialTheme.typography.titleLarge
                                        )
                                        Text(
                                            it.amount,
                                            style = MaterialTheme.typography.titleLarge
                                        )
                                    }

                                    Row(
                                        modifier = Modifier.fillMaxWidth(),
                                        horizontalArrangement = Arrangement.SpaceBetween,
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Text("Largest transaction",
                                            style = MaterialTheme.typography.bodySmall
                                        )
                                        Text(it.timestamp,
                                            style = MaterialTheme.typography.bodySmall
                                        )
                                    }
                                }
                            }
                        }
                        Box(
                            modifier = Modifier.background(
                                color = secondaryFixed,
                                shape = MaterialTheme.shapes.medium
                            )
                                .fillMaxHeight()

                        ) {
                            Column(
                                modifier = Modifier.padding(16.dp)
                            ) {
                                Text(
                                    accountUiState.sumPreviousWeek,
                                    style = MaterialTheme.typography.titleLarge
                                )
                                Text(
                                    "Previous week",
                                    style = MaterialTheme.typography.bodySmall
                                )
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))

                transactionsComponent()
            }

        }
    }
}

@Preview
@Composable
private fun DashboardScreenPreview() {
    val currencyFormatter = CurrencyFormatterFactory(
        context = LocalContext.current
    )
    val preferences = fakePreferences()
    SpendLessTheme {
        DashboardScreen(
            accountUiState = AccountUiState(),
            transactionsComponent = {
                TransactionsComponent(
                    transactions = fakeTransactions().groupByDate().toUi(currencyFormatter, preferences)
                )
            }
        )
    }

}