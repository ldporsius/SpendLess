package nl.codingwithlinda.authentication.onboarding

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import nl.codingwithlinda.authentication.onboarding.components.ExpensesFormatComponent
import nl.codingwithlinda.authentication.onboarding.components.SelectCurrencyComponent
import nl.codingwithlinda.authentication.onboarding.state.OnboardingAction
import nl.codingwithlinda.authentication.onboarding.state.OnboardingUiState

@Composable
fun OnboardingScreen(
    modifier: Modifier = Modifier,
    uiState: OnboardingUiState,
    onAction: (OnboardingAction) -> Unit,
    onNavigate: () -> Unit
) {
    Box(modifier = Modifier
        .fillMaxSize()
        ,
        contentAlignment = Alignment.Center
    ) {
        IconButton(
            onClick = onNavigate,
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(16.dp)
        ) {
            Icon(
                Icons.Default.ArrowBack,
                contentDescription = null,
                tint = Color.Black
            )
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.Start
        ) {
            Text(text = "Set SpendLess",
                style = MaterialTheme.typography.headlineMedium
            )
            Text(text = "to your preferences",
                style = MaterialTheme.typography.headlineMedium
            )
            Text(text = "You can change it at any time in Settings",
                style = MaterialTheme.typography.bodyMedium
            )
            ElevatedCard(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 8.dp
                ),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.background
                )
            ) {
                Column(modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = uiState.exampleFormattedText,
                        style = MaterialTheme.typography.headlineLarge
                    )
                    Text(
                        text = "spent this month",
                        style = MaterialTheme.typography.bodySmall
                    )
                }
            }

            ExpensesFormatComponent(
                selectedExpensesFormat = uiState.preferences.expensesFormat.ordinal,
                onExpensesFormatSelected = {
                    onAction(OnboardingAction.OnSelectExpensesFormat(it))
                }
            )

            var shouldShowCurrencyPicker by remember { mutableStateOf(false) }
            Box(
                Modifier.fillMaxWidth()
            ) {
                Column {
                    Text(
                        text = "Currency",
                        style = MaterialTheme.typography.bodySmall,
                        modifier = Modifier
                    )
                    Button(
                        onClick = { shouldShowCurrencyPicker = true },
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        Text(text = uiState.getCurrencyUi(uiState.preferences.currency).text)
                    }
                    SelectCurrencyComponent(
                        currencies = uiState.currencyUiList(),
                        selectedCurrency = uiState.preferences.currency.ordinal,
                        expanded = shouldShowCurrencyPicker,
                        onDismissRequest = {shouldShowCurrencyPicker = false},
                        onCurrencySelected = {
                            onAction(OnboardingAction.OnSelectCurrency(it))
                            shouldShowCurrencyPicker = false

                        }
                    )
                }
            }

        }
    }

}