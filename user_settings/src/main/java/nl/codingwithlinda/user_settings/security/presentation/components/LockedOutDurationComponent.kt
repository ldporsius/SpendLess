package nl.codingwithlinda.user_settings.security.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
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
import nl.codingwithlinda.core.domain.model.LockedOutDuration
import nl.codingwithlinda.core.domain.model.Separator
import nl.codingwithlinda.core.domain.model.SessionDuration
import nl.codingwithlinda.core_ui.LocalSegmentedButtonColorProvider
import nl.codingwithlinda.user_settings.security.presentation.mapping.toUi

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LockedOutDurationComponent(
    selectedDuration: LockedOutDuration,
    onSelected: (LockedOutDuration) -> Unit
) {

    Column {
        Text(
            text = "Locked out duration",
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
                    onSelected(LockedOutDuration.FIFTEEN_SECONDS)
                },
                selected = selectedDuration == LockedOutDuration.FIFTEEN_SECONDS,
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier.padding(end = 8.dp),
                colors = LocalSegmentedButtonColorProvider.current
            ) {
                Text(text = LockedOutDuration.FIFTEEN_SECONDS.toUi().asString())
            }
            SegmentedButton(
                onClick = {
                    onSelected(LockedOutDuration.THIRTY_SECONDS)
                },
                selected = selectedDuration == LockedOutDuration.THIRTY_SECONDS,
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier.padding(end = 8.dp),
                colors = LocalSegmentedButtonColorProvider.current
            ) {
                Text(text = LockedOutDuration.THIRTY_SECONDS.toUi().asString())
            }
            SegmentedButton(
                onClick = {
                    onSelected(LockedOutDuration.ONE_MINUTE)
                },
                selected = selectedDuration == LockedOutDuration.ONE_MINUTE,
                shape = RoundedCornerShape(16.dp),
                colors = LocalSegmentedButtonColorProvider.current
            ) {
                Text(text = LockedOutDuration.ONE_MINUTE.toUi().asString())
            }
            SegmentedButton(
                onClick = {
                    onSelected(LockedOutDuration.FIVE_MINUTES)
                },
                selected = selectedDuration == LockedOutDuration.FIVE_MINUTES,
                shape = RoundedCornerShape(16.dp),
                colors = LocalSegmentedButtonColorProvider.current
            ) {
                Text(text = LockedOutDuration.FIVE_MINUTES.toUi().asString())
            }
        }
    }
}