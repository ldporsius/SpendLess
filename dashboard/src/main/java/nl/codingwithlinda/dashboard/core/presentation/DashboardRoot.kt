package nl.codingwithlinda.dashboard.core.presentation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import nl.codingwithlinda.authentication.core.domain.usecase.LoggedInAccountUseCase
import nl.codingwithlinda.core.di.AppModule
import nl.codingwithlinda.core.domain.session_manager.SessionManager
import nl.codingwithlinda.core_ui.currency.CurrencyFormatterFactory
import nl.codingwithlinda.dashboard.categories.data.CategoryFactory
import nl.codingwithlinda.dashboard.core.domain.usecase.PreferencesForAccountUseCase
import nl.codingwithlinda.dashboard.core.domain.usecase.TestTransactionsUseCase
import nl.codingwithlinda.dashboard.core.domain.usecase.TransactionForAccountUseCase
import nl.codingwithlinda.dashboard.transactions.recent.presentation.TransactionsComponent
import nl.codingwithlinda.dashboard.transactions.transaction_create.presentation.CreateTransactionScreen
import nl.codingwithlinda.dashboard.transactions.transaction_create.presentation.CreateTransactionViewModel

@Composable
fun DashboardRoot(
    appModule: AppModule,
    onShowAll: () -> Unit,
    onNavToSettings: ()->Unit
) {

    val context = LocalContext.current
    val categoryFactory = CategoryFactory(context)

    val currencyFormatterFactory = CurrencyFormatterFactory(
        context = context
    )
    val sessionManager: SessionManager = appModule.sessionManager
    val loggedInAccountUseCase =
        LoggedInAccountUseCase(
            appModule.accountAccessReadOnly,
            sessionManager
        )
    val transactionForAccountUseCase = TransactionForAccountUseCase(appModule.transactionsAccess, sessionManager)
    val preferencesForAccountUseCase = PreferencesForAccountUseCase(appModule.preferencesAccessForAccount, sessionManager)
    val testTransactionsUseCase = TestTransactionsUseCase(appModule.transactionsAccess, sessionManager)

    val factory = viewModelFactory {
        initializer {
            DashboardViewModel(
                categoryFactory = categoryFactory,
                currencyFormatterFactory = currencyFormatterFactory,
                loggedInAccountUseCase = loggedInAccountUseCase,
                transactionForAccountUseCase = transactionForAccountUseCase,
                preferencesForAccountUseCase = preferencesForAccountUseCase,
                testTransactionsUseCase = testTransactionsUseCase,
            )
        }
    }
    val viewModel = viewModel<DashboardViewModel>(
        factory = factory
    )
    val createTransactionViewModelFactory = viewModelFactory {
        initializer {
            CreateTransactionViewModel()
        }
    }
    val createTransactionViewModel = viewModel<CreateTransactionViewModel>(
        factory = createTransactionViewModelFactory
    )

    DashboardScreen(
        accountUiState = viewModel.accountUiState.collectAsStateWithLifecycle().value,
        onNavToSettings = {
            onNavToSettings()
        },
        transactionsComponent = {
            TransactionsComponent(
                transactions = viewModel.transactions.collectAsStateWithLifecycle().value,
                onShowAll = onShowAll
            )
        },
        transactionUiState = viewModel.dashboardCreateTransactionUiState.collectAsStateWithLifecycle().value,
        onCreateTransaction = viewModel::onCreateTransaction,
        createTransactionComponent = {
            CreateTransactionScreen(
                modifier = Modifier,
                uiState = createTransactionViewModel.uiState.collectAsStateWithLifecycle().value,
                onAction = createTransactionViewModel::handleAction
            )
        }
    )
}