package nl.codingwithlinda.dashboard.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import nl.codingwithlinda.core.navigation.DashboardNavRoute

@Composable
fun DashboardRoot() {

    Scaffold {padding ->
        Box(modifier = Modifier.padding(padding)) {
            Text("Dashboard")
        }
    }
}