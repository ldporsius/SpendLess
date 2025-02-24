package nl.codingwithlinda.dashboard.transactions.transactions_all.presentation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import nl.codingwithlinda.core.di.AppModule
import nl.codingwithlinda.core_ui.currency.CurrencyFormatterFactory

@Composable
fun AllTransactionsRoot(
    appModule: AppModule,
    onNavBack: () -> Unit
) {
    val currencyFormatterFactory = CurrencyFormatterFactory(LocalContext.current)

    val factory = viewModelFactory {
        initializer {
            TransactionsAllViewModel(
                appModule = appModule,
                currencyFormatterFactory = currencyFormatterFactory,
            )
        }
    }

    val viewModel = viewModel<TransactionsAllViewModel>(factory = factory)

    AllTransactionsScreen(
        transactions = viewModel.transactions.collectAsStateWithLifecycle().value,
        onNavBack = {
            onNavBack()
        }

    )

}