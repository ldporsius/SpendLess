package nl.codingwithlinda.core.data.util

import java.math.BigDecimal

fun stringToBigDecimal(value: String): BigDecimal {
    val bd = try {
        println("STRING TO BIG DECIMAL ORIGINAL VALUE: $value")
        if(value.isEmpty()) return BigDecimal.ZERO

        val indexOfAnySep = value
            //.filterNot { it.toString() == "-" }
            .indexOfAny(listOf(",", "."))

        println("indexOfAnySep: $indexOfAnySep")

        if(indexOfAnySep == -1) return BigDecimal(value)

        val thousands = value
            //.filter { it.isDigit() }
            .foldIndexed("") { index, acc, c ->
            if (index < indexOfAnySep) {
                "$acc$c"
            } else {
                acc
            }
        }
        val decimals = value
            .substring(indexOfAnySep)
            .filter { it.isDigit() }
            .padEnd(2, '0')
            .take(2)
        val sepToken =  "."
        val input = thousands + sepToken + decimals

        println("thousands: $thousands")
        println("decimals: $decimals")
        println("INPUT: $input")

        BigDecimal(input)

    }catch (e: Exception){
        e.printStackTrace()
        BigDecimal.ZERO
    }

    println("STRING TO BIG DECIMAL RETURN VALUE: $bd")
    println("********************************************")
    return bd
}