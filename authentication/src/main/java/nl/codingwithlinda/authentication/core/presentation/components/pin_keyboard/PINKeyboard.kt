package nl.codingwithlinda.authentication.core.presentation.components.pin_keyboard

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import nl.codingwithlinda.authentication.core.presentation.components.pin_keyboard.state.PINKeyboardAction
import nl.codingwithlinda.core.R
import nl.codingwithlinda.core.ui.onPrimaryFixed

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun PINKeyboard(
    modifier: Modifier = Modifier,
    onPINKeyboardAction: (PINKeyboardAction) -> Unit
) {

    Column {
        FlowRow(
            modifier = modifier,
            maxItemsInEachRow = 3,
            verticalArrangement = Arrangement.spacedBy(4.dp),
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            for (i in (1..12)) {
                if (i < 10) {
                    PINKeyboardButton(
                        text = {
                            Text(
                                i.toString(),
                                style = MaterialTheme.typography.headlineMedium,
                                color = onPrimaryFixed
                            )
                        },
                        modifier = Modifier.clickable {
                            onPINKeyboardAction(PINKeyboardAction.OnNumberClick(i))
                        }
                    )
                }
                if (i == 10){
                    PINKeyboardEmptyButton(
                    )
                }
                if (i == 11){
                    PINKeyboardButton(
                        text = {
                            Text(
                                "0",
                                style = MaterialTheme.typography.headlineMedium,
                                color = onPrimaryFixed
                            )
                        },
                        modifier = Modifier.clickable {
                            onPINKeyboardAction(PINKeyboardAction.OnNumberClick(0))
                        }
                    )
                }
                if (i == 12){
                    PINKeyboardUndoButton (
                        text = {
                            Icon(
                                painter = painterResource(R.drawable.backspace),
                                contentDescription = null,
                                modifier = Modifier.size(40.dp),
                                tint = onPrimaryFixed
                            )
                        },
                        modifier = Modifier.clickable {
                            onPINKeyboardAction(PINKeyboardAction.OnUndoClick)
                        }
                    )
                }
            }
        }

    }
}