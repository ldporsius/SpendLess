package nl.codingwithlinda.core_ui.currency

import nl.codingwithlinda.core.domain.model.Separator


val decimalSeparatorMap = mapOf(
    Separator.COMMA to ",",
    Separator.PERIOD to ".",
)

val thousandsSeparatorMap = mapOf(
    Separator.COMMA to ",",
    Separator.PERIOD to ".",
    Separator.SPACE to " "

)

fun Separator.toUi(): String {
    return when(this){
        Separator.COMMA -> ","
        Separator.PERIOD -> "."
        Separator.SPACE -> " "
    }
}