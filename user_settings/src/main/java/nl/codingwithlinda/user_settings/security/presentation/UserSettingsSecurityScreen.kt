package nl.codingwithlinda.user_settings.security.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import nl.codingwithlinda.user_settings.security.presentation.components.LockedOutDurationComponent
import nl.codingwithlinda.user_settings.security.presentation.components.SessionDurationComponent
import nl.codingwithlinda.user_settings.security.presentation.state.SecurityAction
import nl.codingwithlinda.user_settings.security.presentation.state.SecurityUiState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserSettingsSecurityScreen(
    uiState: SecurityUiState,
    onAction: (SecurityAction) -> Unit,
    onNavBack: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    androidx.compose.material3.Text(text = "Security")
                },
                navigationIcon = {
                    androidx.compose.material3.IconButton(
                        onClick = {
                           onNavBack()
                        }
                    ) {
                        androidx.compose.material3.Icon(
                            imageVector = androidx.compose.material.icons.Icons.Default.ArrowBack, contentDescription = null)
                        }
                }
            )
        }
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {

            Column(
                modifier = Modifier.fillMaxSize()
                    .padding(top = 48.dp, start = 16.dp, end = 16.dp),
                horizontalAlignment = androidx.compose.ui.Alignment.Start,
                verticalArrangement = Arrangement.spacedBy(48.dp)
            ) {
                SessionDurationComponent(
                    selectedDuration = uiState.selectedSessionDuration,
                ) {
                    onAction(SecurityAction.SelectSessionDuration(it))
                }

                LockedOutDurationComponent(
                    selectedDuration = uiState.selectedLockedOutDuration,
                ) {
                    onAction(SecurityAction.SelectLockedOutDuration(it))
                }

                Button(
                    onClick = { /*TODO*/ }
                    ,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = "Save")
                }
            }
        }
    }
}