package nl.codingwithlinda.core_ui.util

import java.math.BigDecimal

fun stringToThousandsAndDecimals(value: String): Pair<String, String>{
    val bd = stringToBigDecimal(value)

    val thousands = bd.toBigInteger().toString()
    val decimals = bd.remainder(BigDecimal.ONE).movePointRight(2)
        .toString().padEnd(2, '0')

    println("STRINGTOTHOUSANDDECIMALS. THOUSANDS: $thousands DECIMALS: $decimals")
    return Pair(thousands, decimals)
}