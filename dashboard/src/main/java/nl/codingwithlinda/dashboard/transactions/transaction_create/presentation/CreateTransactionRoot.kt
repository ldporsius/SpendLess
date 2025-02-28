package nl.codingwithlinda.dashboard.transactions.transaction_create.presentation

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import nl.codingwithlinda.authentication.core.domain.usecase.LoggedInAccountUseCase
import nl.codingwithlinda.core.data.dto.TransactionDto
import nl.codingwithlinda.core.di.AppModule
import nl.codingwithlinda.core.domain.session_manager.SessionManager
import nl.codingwithlinda.core_ui.currency.CurrencyFormatterFactory
import nl.codingwithlinda.dashboard.categories.common.data.CategoryFactory
import nl.codingwithlinda.dashboard.core.domain.usecase.PreferencesForAccountUseCase
import nl.codingwithlinda.dashboard.core.domain.usecase.TestTransactionsUseCase
import nl.codingwithlinda.dashboard.core.domain.usecase.TransactionForAccountUseCase
import nl.codingwithlinda.dashboard.transactions.transaction_create.domain.usecase.CreateTransactionUseCase
import nl.codingwithlinda.dashboard.transactions.transaction_create.domain.usecase.SaveTransactionUseCase

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateTransactionRoot(
    appModule: AppModule,
    onCreateTransaction: (TransactionDto) -> Unit,
    onNavBack: () -> Unit
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
    val createTransactionUseCase = CreateTransactionUseCase(
        sessionManager = sessionManager,
        authenticationManager = appModule.authenticationManager
    )
    val saveTransactionUseCase = SaveTransactionUseCase(
        transactionsAccess = appModule.transactionsAccess
    )
    val createTransactionViewModelFactory = viewModelFactory {
        initializer {
            CreateTransactionViewModel(
                currencyFormatterFactory = currencyFormatterFactory,
                preferencesForAccountUseCase = preferencesForAccountUseCase,
                createTransactionUseCase = createTransactionUseCase,
                saveTransactionUseCase = saveTransactionUseCase,
                onNavAction = {
                    onCreateTransaction(it)
                },
                onNavBack = {
                    onNavBack()
                }
            )
        }
    }
    val createTransactionViewModel = viewModel<CreateTransactionViewModel>(
        factory = createTransactionViewModelFactory
    )


    Scaffold(
        topBar = {
            TopAppBar(
                title = {

                },
                actions = {
                    IconButton(
                        onClick = {
                            onNavBack()
                        }
                    ) {
                        Icon(imageVector = Icons.Default.Close, contentDescription = "close")
                    }
                }
            )
        }
    ) { paddingValues ->

        CreateTransactionScreen(
            modifier = Modifier.padding(paddingValues),
            uiState = createTransactionViewModel.uiState.collectAsStateWithLifecycle().value,
            onAction = {
                createTransactionViewModel.handleAction(it)
            },
            categoryPicker = {
                //todo
            },
        )
    }
}