package nl.codingwithlinda.dashboard.transactions.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import nl.codingwithlinda.core_ui.primaryFixed

@Composable
fun TransactionItemModifier() = Modifier
    .size(48.dp)
    .background(
        color = primaryFixed,
        shape = MaterialTheme.shapes.small
    ).padding(8.dp)