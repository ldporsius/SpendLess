package nl.codingwithlinda.dashboard.core.presentation

import androidx.compose.runtime.Composable
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import nl.codingwithlinda.core.di.AppModule
import nl.codingwithlinda.dashboard.transactions.presentation.TransactionsComponent

@Composable
fun DashboardRoot(
    appModule: AppModule
) {

    val factory = viewModelFactory {
        initializer {
            DashboardViewModel(appModule)
        }
    }
    val viewModel = viewModel<DashboardViewModel>(
        factory = factory
    )

    DashboardScreen(
        accountUiState = viewModel.accountUiState.collectAsStateWithLifecycle().value,
        transactionsComponent = {
            TransactionsComponent(
                transactions = viewModel.transactions.collectAsStateWithLifecycle().value
            )
        }
    )
}