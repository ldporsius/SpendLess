package nl.codingwithlinda.core_ui.util

import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Test
import java.math.BigDecimal

class StringToBigDecimalKtTest{

    @Test
    fun `test string to bigdecimal conversion comma`() = runBlocking {
        val input = "123,45"
        val result = stringToBigDecimal(input)

        println("RESULT: $result")
        assertEquals(BigDecimal("123.45"), result)
    }
    @Test
    fun `test string to bigdecimal conversion period`() = runBlocking {
        val input = "123.45"
        val result = stringToBigDecimal(input)

        println("RESULT: $result")
        assertEquals(BigDecimal("123.45"), result)
    }
    @Test
    fun `test string to bigdecimal conversion no sep token`() = runBlocking {
        val input = "123"
        val result = stringToBigDecimal(input)

        println("RESULT: $result")
        assertEquals(BigDecimal("123.00"), result)
    }

    @Test
    fun `test string to bigdecimal conversion multiple sep tokens`() = runBlocking {
        val input = "123,.00"
        val result = stringToBigDecimal(input)

        println("RESULT: $result")
        assertEquals(BigDecimal("123.00"), result)
    }

    @Test
    fun `test string to bigdecimal conversion multiple sep tokens 2`() = runBlocking {
        val input = "1,.23,.00"
        val result = stringToBigDecimal(input)

        println("RESULT: $result")
        assertEquals(BigDecimal("1.23"), result)
    }
}