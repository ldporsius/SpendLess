package nl.codingwithlinda.authentication.onboarding.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SegmentedButton
import androidx.compose.material3.SingleChoiceSegmentedButtonRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import nl.codingwithlinda.core.domain.model.Separator
import nl.codingwithlinda.core_ui.LocalSegmentedButtonColorProvider

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ThousandsSeparatorComponent(
    selectedSeparator: Int,
    onSelected: (Separator) -> Unit
) {


    Text(text = "Thousands separator",
        style = MaterialTheme.typography.bodySmall)

    SingleChoiceSegmentedButtonRow(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = MaterialTheme.colorScheme.surfaceContainerHigh,
                shape = RoundedCornerShape(16.dp)
            )
            .padding(horizontal = 1.dp)
        ,

        ) {
        SegmentedButton(
            onClick = {
                onSelected(Separator.PERIOD)
            },
            selected = selectedSeparator == 0,
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier.padding(end = 8.dp),
            colors = LocalSegmentedButtonColorProvider.current
        ) {
            Text(text = "1.000")
        }
        SegmentedButton(
            onClick = {
                onSelected(Separator.COMMA)
            },
            selected = selectedSeparator == 1,
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier.padding(end = 8.dp),
            colors = LocalSegmentedButtonColorProvider.current
        ) {
            Text(text = "1,000")
        }
        SegmentedButton(
            onClick = {
                onSelected(Separator.SPACE)
            },
            selected = selectedSeparator == 2,
            shape = RoundedCornerShape(16.dp),
            colors = LocalSegmentedButtonColorProvider.current
        ) {
            Text(text = "1 000")
        }
    }
}