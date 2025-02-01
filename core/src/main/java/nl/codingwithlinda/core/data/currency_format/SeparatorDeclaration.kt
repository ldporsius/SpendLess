package nl.codingwithlinda.core.data.currency_format

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