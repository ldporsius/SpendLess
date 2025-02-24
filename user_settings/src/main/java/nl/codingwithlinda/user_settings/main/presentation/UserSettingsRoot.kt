package nl.codingwithlinda.user_settings.main.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import nl.codingwithlinda.core_ui.shared_components.CustomColoredIconButton
import nl.codingwithlinda.user_settings.main.presentation.state.SettingsAction

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserSettingsRoot(
    onNavBack: () -> Unit,
    onAction: (SettingsAction) -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Settings")
                },
                navigationIcon = {
                    IconButton(onClick = onNavBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back",)
                    }
                },
                colors = androidx.compose.material3.TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Transparent
                )
            )
        }
    ) {paddingValues ->

        Column(
            modifier = Modifier.padding(paddingValues)
        ) {

            Box(modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .shadow(
                    elevation = 4.dp,
                    shape = androidx.compose.material3.MaterialTheme.shapes.medium
                )
                .background(
                    color = androidx.compose.material3.MaterialTheme.colorScheme.surfaceContainerLowest,
                    shape = androidx.compose.material3.MaterialTheme.shapes.medium
                )
            ){
                Column(
                    modifier = Modifier.padding(0.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth()
                            .padding(4.dp)
                            .clickable {
                                onAction(SettingsAction.NavigateToPreferences)
                            }
                        ,
                        verticalAlignment = androidx.compose.ui.Alignment.CenterVertically
                    ) {
                        CustomColoredIconButton(
                            modifier = Modifier.padding(end = 16.dp),
                            backgroundColor = androidx.compose.material3.MaterialTheme.colorScheme.surfaceContainerLow,
                        ) {
                            Icon(painter = painterResource(nl.codingwithlinda.core_ui.R.drawable.settings),
                                contentDescription = "Preferences",
                                tint = androidx.compose.material3.MaterialTheme.colorScheme.onSurfaceVariant)
                        }
                        Text(text = "Preferences")
                    }

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(4.dp)
                            .clickable {
                                onAction(SettingsAction.NavigateToSecurity)
                            }
                        ,
                        verticalAlignment = androidx.compose.ui.Alignment.CenterVertically
                    ) {
                        CustomColoredIconButton(
                            modifier = Modifier.padding(end = 16.dp),
                            backgroundColor = androidx.compose.material3.MaterialTheme.colorScheme.surfaceContainerLow,
                        ) {
                            Icon(painter = painterResource(nl.codingwithlinda.core_ui.R.drawable.lock),
                                contentDescription = "Security",
                                tint = androidx.compose.material3.MaterialTheme.colorScheme.onSurfaceVariant)
                        }
                        Text(text = "Security")
                    }
                }
            }

            Box(modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .shadow(
                    elevation = 4.dp,
                    shape = androidx.compose.material3.MaterialTheme.shapes.medium
                )
                .background(
                    color = androidx.compose.material3.MaterialTheme.colorScheme.surfaceContainerLowest,
                    shape = androidx.compose.material3.MaterialTheme.shapes.medium
                )
            ){
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(4.dp)
                        .clickable {
                            onAction(SettingsAction.NavigateToLogout)
                        }
                    ,
                    verticalAlignment = androidx.compose.ui.Alignment.CenterVertically
                ) {
                    CustomColoredIconButton(
                        modifier = Modifier.padding(end = 16.dp),
                        backgroundColor = androidx.compose.material3.MaterialTheme.colorScheme.error.copy(0.08f),
                    ) {
                        Icon(painter = painterResource(nl.codingwithlinda.core_ui.R.drawable.logout),
                            contentDescription = "Logout",
                            tint = androidx.compose.material3.MaterialTheme.colorScheme.error)
                    }
                    Text(text = "Logout")
                }
            }
        }

    }
}