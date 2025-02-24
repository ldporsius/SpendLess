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
import nl.codingwithlinda.core.domain.model.Separator
import nl.codingwithlinda.core.domain.model.SessionDuration
import nl.codingwithlinda.core_ui.LocalSegmentedButtonColorProvider
import nl.codingwithlinda.user_settings.security.presentation.mapping.toUi

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SessionDurationComponent(
    selectedDuration: SessionDuration,
    onSelected: (SessionDuration) -> Unit
) {

    Column {
        Text(
            text = "Session expiry duration",
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
                    onSelected(SessionDuration.FIVE_MINUTES)
                },
                selected = selectedDuration == SessionDuration.FIVE_MINUTES,
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier.padding(end = 8.dp),
                colors = LocalSegmentedButtonColorProvider.current
            ) {
                Text(text = SessionDuration.FIVE_MINUTES.toUi().asString())
            }
            SegmentedButton(
                onClick = {
                    onSelected(SessionDuration.FIFTEEN_MINUTES)
                },
                selected = selectedDuration == SessionDuration.FIFTEEN_MINUTES,
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier.padding(end = 8.dp),
                colors = LocalSegmentedButtonColorProvider.current
            ) {
                Text(text = SessionDuration.FIFTEEN_MINUTES.toUi().asString())
            }
            SegmentedButton(
                onClick = {
                    onSelected(SessionDuration.THIRTY_MINUTES)
                },
                selected = selectedDuration == SessionDuration.THIRTY_MINUTES,
                shape = RoundedCornerShape(16.dp),
                colors = LocalSegmentedButtonColorProvider.current
            ) {
                Text(text = SessionDuration.THIRTY_MINUTES.toUi().asString())
            }
            SegmentedButton(
                onClick = {
                    onSelected(SessionDuration.ONE_HOUR)
                },
                selected = selectedDuration == SessionDuration.ONE_HOUR,
                shape = RoundedCornerShape(16.dp),
                colors = LocalSegmentedButtonColorProvider.current
            ) {
                Text(text = SessionDuration.ONE_HOUR.toUi().asString())
            }
        }
    }
}