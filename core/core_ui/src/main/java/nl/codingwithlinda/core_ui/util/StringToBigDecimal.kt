package nl.codingwithlinda.core_ui.util

import java.math.BigDecimal

fun stringToBigDecimal(value: String): BigDecimal {
    val bd = try {
        println("ORIGINAL VALUE: $value")
        if(value.isEmpty()) return BigDecimal.ZERO

        val indexOfAnySep = value
            .filterNot { it.toString() == "-" }
            .indexOfAny(listOf(",", ".")).let {
            if(it == -1) value.length else it
        }

        println("indexOfAnySep: $indexOfAnySep")

        val thousands = value.filter { it.isDigit() }.foldIndexed("") { index, acc, c ->
            if (index < indexOfAnySep) {
                "$acc$c"
            } else {
                acc
            }
        }
        val decimals = value
            .substring(indexOfAnySep)
            .filter { it.isDigit() }

            .padEnd(2, '0').take(2)
        val input = thousands + "." + decimals

        println("thousands: $thousands")
        println("decimals: $decimals")
        println("INPUT: $input")

        BigDecimal(input)

    }catch (e: Exception){
        e.printStackTrace()
        BigDecimal.ZERO
    }

    return bd
}