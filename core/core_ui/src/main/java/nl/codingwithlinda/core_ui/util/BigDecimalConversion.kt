package nl.codingwithlinda.core_ui.util

import java.math.BigDecimal
import java.math.RoundingMode

fun BigDecimal.scaleToTwoDecimalPlaces(): String{
    return this.setScale(2, RoundingMode.HALF_UP).toPlainString()
}