package nl.codingwithlinda.dashboard.transactions.common.previews

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import nl.codingwithlinda.core.test_data.test_data_generators.fakePreferences
import nl.codingwithlinda.core.test_data.test_data_generators.fakeTransactions
import nl.codingwithlinda.core_ui.SpendLessTheme
import nl.codingwithlinda.core_ui.currency.CurrencyFormatterFactory
import nl.codingwithlinda.core_ui.date_time.DateTimeConverter
import nl.codingwithlinda.dashboard.categories.common.data.CategoryFactory
import nl.codingwithlinda.dashboard.core.presentation.DashboardScreen
import nl.codingwithlinda.dashboard.core.presentation.state.AccountUiState
import nl.codingwithlinda.dashboard.core.presentation.state.DashboardCreateTransactionUiState
import nl.codingwithlinda.dashboard.transactions.common.components.TransactionItemExpanded
import nl.codingwithlinda.dashboard.transactions.common.ui_model.TransactionUi
import nl.codingwithlinda.dashboard.transactions.common.ui_model.mapping.groupByDate
import nl.codingwithlinda.dashboard.transactions.common.ui_model.mapping.toUi
import nl.codingwithlinda.dashboard.transactions.recent.presentation.TransactionsComponent


@SuppressLint("NewApi")
@Preview
@Composable
private fun TransactionsComponentPreview() {
    val context = LocalContext.current
    val currencyFormatter = CurrencyFormatterFactory(
        context = context
    )
    val categoryFactory = CategoryFactory(context)
    val preferences = fakePreferences()
    SpendLessTheme {
        TransactionsComponent(
            modifier = Modifier.fillMaxWidth(),
            transactions = fakeTransactions("1").groupByDate().toUi(
                currencyFormatterFactory = currencyFormatter,
                preferences = preferences,
                formatter = DateTimeConverter.MEDIUM_DATE,
                categoryFactory = TODO()
            ),
            onShowAll = {}
        )
    }
}

@Preview
@Composable
private fun TransactionItemExpandedPreview() {
    SpendLessTheme {
        TransactionItemExpanded(
            context = LocalContext.current,
            transaction = TransactionUi(
                //id = "1",
            )
        )

    }
}


@SuppressLint("NewApi")
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
                    transactions = fakeTransactions(
                        "1"
                    ).groupByDate().toUi(
                        categoryFactory = CategoryFactory(LocalContext.current),
                        currencyFormatter,
                        preferences,
                        formatter = DateTimeConverter.SHORT_DATE
                    ),
                    onShowAll = {}
                )
            },
            onNavToSettings = { },
            transactionUiState = DashboardCreateTransactionUiState(),
            onCreateTransaction = {},
            createTransactionComponent = {

            }
        )
    }

}