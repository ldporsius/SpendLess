package nl.codingwithlinda.core.presentation.util

import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource

sealed class UiText {

    data class DynamicText(val value: String): UiText()

    data class StringResource(@StringRes val resId: Int,
                              val args: List<Any>): UiText()

    @Composable
    fun asString(): String {
        return when(this){
            is DynamicText -> this.value
            is StringResource -> stringResource(id = this.resId, formatArgs = this.args.toTypedArray())
        }
    }
}