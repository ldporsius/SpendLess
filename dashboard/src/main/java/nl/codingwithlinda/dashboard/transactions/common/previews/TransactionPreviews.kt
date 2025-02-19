package nl.codingwithlinda.dashboard.transactions.common.previews

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import nl.codingwithlinda.core.test_data.fakePreferences
import nl.codingwithlinda.core.test_data.fakeTransactions
import nl.codingwithlinda.core_ui.SpendLessTheme
import nl.codingwithlinda.core_ui.currency.CurrencyFormatterFactory
import nl.codingwithlinda.dashboard.core.presentation.DashboardScreen
import nl.codingwithlinda.dashboard.core.presentation.state.AccountUiState
import nl.codingwithlinda.dashboard.transactions.common.components.TransactionItemExpanded
import nl.codingwithlinda.dashboard.transactions.common.ui_model.TransactionUi
import nl.codingwithlinda.dashboard.transactions.common.ui_model.mapping.groupByDate
import nl.codingwithlinda.dashboard.transactions.common.ui_model.mapping.toUi
import nl.codingwithlinda.dashboard.transactions.recent.presentation.TransactionsComponent



@Preview
@Composable
private fun TransactionsComponentPreview() {
    val currencyFormatter = CurrencyFormatterFactory(
        context = LocalContext.current
    )
    val preferences = fakePreferences()
    SpendLessTheme {
        TransactionsComponent(
            modifier = Modifier.fillMaxWidth(),
            transactions = fakeTransactions("1").groupByDate().toUi(
                currencyFormatterFactory = currencyFormatter,
                preferences = preferences
            )
        )
    }
}

@Preview
@Composable
private fun TransactionItemExpandedPreview() {
    SpendLessTheme {
        TransactionItemExpanded(
            context = LocalContext.current,
            transaction = TransactionUi()
        )

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
                    transactions = fakeTransactions(
                        "1"
                    ).groupByDate().toUi(currencyFormatter, preferences)
                )
            }
        )
    }

}