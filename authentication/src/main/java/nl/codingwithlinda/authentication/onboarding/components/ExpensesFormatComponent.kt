package nl.codingwithlinda.authentication.onboarding.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SegmentedButton
import androidx.compose.material3.SegmentedButtonColors
import androidx.compose.material3.SingleChoiceSegmentedButtonRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import nl.codingwithlinda.core.domain.model.ExpensesFormat
import nl.codingwithlinda.core_ui.SegmentedButtonColorProvider
import nl.codingwithlinda.core_ui.segmentedButtonColors

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExpensesFormatComponent(
    selectedExpensesFormat: Int,
    onExpensesFormatSelected: (ExpensesFormat) -> Unit
) {

    CompositionLocalProvider(SegmentedButtonColorProvider provides segmentedButtonColors()) {
        Text(
            text = "Expenses format",
            style = MaterialTheme.typography.bodySmall
        )

        SingleChoiceSegmentedButtonRow(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = MaterialTheme.colorScheme.surfaceContainerHigh,
                    shape = RoundedCornerShape(16.dp)
                )
                .padding(horizontal = 1.dp),

            ) {
            SegmentedButton(
                onClick = {
                    onExpensesFormatSelected(ExpensesFormat.MINUS)
                },
                selected = selectedExpensesFormat == 0,
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier.padding(end = 8.dp),
                colors = SegmentedButtonColorProvider.current
            ) {
                Text(text = "-$10")
            }
            SegmentedButton(
                onClick = {
                    onExpensesFormatSelected(ExpensesFormat.BRACKETS)
                },
                selected = selectedExpensesFormat == 1,
                shape = RoundedCornerShape(16.dp),
                colors = SegmentedButtonColorProvider.current
            ) {
                Text(text = "($10)")
            }
        }
    }
}