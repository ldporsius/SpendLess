package nl.codingwithlinda.dashboard.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import nl.codingwithlinda.core_ui.dashboardBackground

@Composable
fun DashboardScreen(modifier: Modifier = Modifier) {
    Scaffold(
        modifier = Modifier
            .fillMaxSize()

    ) {padding ->
        Box(modifier = Modifier
            .fillMaxSize()
            .background(dashboardBackground)
            .padding(padding)


        ) {
            Text("Dashboard")
        }
    }
}