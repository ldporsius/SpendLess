package nl.codingwithlinda.core.presentation.util

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource

sealed interface UiText {

    data class DynamicText(val value: String): UiText
    data class StringResource(val resId: Int, val args: List<Any>): UiText

    @Composable
    fun UiText.asString(){
        when(this){
            is DynamicText -> this.value
            is StringResource -> stringResource(id = this.resId, formatArgs = this.args.toTypedArray())
        }
    }
}