package nl.codingwithlinda.core_ui.util

import org.junit.Assert.*
import org.junit.Test
import java.math.BigDecimal
import java.math.RoundingMode

class BigDecimalConversionKtTest{

    @Test
    fun test_big_decimal_conversion(){

        val value = BigDecimal.TEN

        assertEquals("10", value.asPlainString())

        assertEquals("10", value.toEngineeringString())

        println("SCALE: ${value.scale()}")

        val newValue = value.setScale(2)
        println("SCALE: ${newValue.scale()}, VALUE: $newValue")

        val newValue2 = BigDecimal(10.01)
        val valueHalfUp = newValue2.setScale(2, RoundingMode.HALF_UP)

        println("SCALE2: ${valueHalfUp.scale()}, VALUE2: $valueHalfUp")
    }


}