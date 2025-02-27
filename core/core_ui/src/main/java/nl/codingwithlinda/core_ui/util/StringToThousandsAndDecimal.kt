package nl.codingwithlinda.core_ui.util

fun stringToThousandsAndDecimals(value: String): Pair<String, String>{
    if(value.isEmpty()) return Pair("", "")
    val bd = value.split(",", ".")

    val thousands = bd.first()
    if(bd.size == 1) return Pair(thousands, "")
    val decimals = bd.subList(1, bd.size)
        .joinToString("")
        .take(2)

    val decimalsString = "." + decimals

    println("STRINGTOTHOUSANDDECIMALS. THOUSANDS: $thousands DECIMALS: $decimals")
    return Pair(thousands, decimalsString)
}