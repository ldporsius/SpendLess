package nl.codingwithlinda.core_ui.shared_components

import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import nl.codingwithlinda.core_ui.transparentTextFieldColors

@Composable
fun TransparentTextField(
    modifier: Modifier = Modifier,
    text: String,
    prefix: @Composable (() -> Unit)? = null,
    suffix: @Composable (() -> Unit)? = null,
    onValueChange: (String) -> Unit,
    placeholder: @Composable (() -> Unit)? = null,
    isSingleLine: Boolean = true,
    keyboardType: KeyboardType = KeyboardType.Text,
    keyboardActions: KeyboardActions,
    imeAction: ImeAction = ImeAction.Done
) {

        OutlinedTextField(
            modifier = modifier,
            value = text,
            textStyle = TextStyle(
                textAlign = TextAlign.Center,
            ),
            onValueChange = onValueChange,
            placeholder = placeholder,
            prefix = prefix,
            suffix = suffix,
            singleLine = isSingleLine,
            keyboardOptions = KeyboardOptions(
                keyboardType = keyboardType,
                imeAction = imeAction
            ),
            keyboardActions = keyboardActions,
            colors = transparentTextFieldColors()
        )

}