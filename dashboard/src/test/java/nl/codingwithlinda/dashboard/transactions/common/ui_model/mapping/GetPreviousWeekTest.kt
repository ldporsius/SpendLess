package nl.codingwithlinda.dashboard.transactions.common.ui_model.mapping

import org.junit.Assert.*
import org.junit.Test
import java.time.DayOfWeek
import java.time.ZonedDateTime

class GetPreviousWeekTest{

    @Test
    fun `get previous week test - same day is zero`(){
        val today = ZonedDateTime.now().with(DayOfWeek.MONDAY)
        val lastMonday = ZonedDateTime.now().with(DayOfWeek.MONDAY)
        println("Last Monday: $lastMonday")
        val result = getPreviousWeek(
            today = today,
            lastMonday.toInstant().toEpochMilli())

        println("Result: $result")
        assertEquals(0, result)
    }

    @Test
    fun `get previous week test - week fully past is 7`(){
        val today = ZonedDateTime.now().with(DayOfWeek.MONDAY)
        val lastMonday = ZonedDateTime.now().with(DayOfWeek.MONDAY).minusWeeks(1)
        println("Last Monday: $lastMonday")
        val result = getPreviousWeek(
            today = today,
            lastMonday.toInstant().toEpochMilli())

        println("Result: $result")
        assertEquals(7, result)
    }

    @Test
    fun `get previous week test - week half past is 1`(){
        val today = ZonedDateTime.now().with(DayOfWeek.WEDNESDAY)
        val lastMonday = ZonedDateTime.now().with(DayOfWeek.WEDNESDAY).minusDays(4)
        println("Last Monday: $lastMonday, " +
                "${lastMonday.dayOfWeek.getDisplayName(
                    java.time.format.TextStyle.FULL,
                    java.util.Locale.getDefault()
                )}")
        val result = getPreviousWeek(
            today = today,
            lastMonday.toInstant().toEpochMilli())

        println("Result: $result")
        assertEquals(1, result)
    }

    @Test
    fun `get previous week test - week half past is 11`(){
        val today = ZonedDateTime.now().with(DayOfWeek.WEDNESDAY)
        val lastMonday = ZonedDateTime.now().with(DayOfWeek.WEDNESDAY)
            .minusWeeks(1).minusDays(2)
        println("Last Monday: $lastMonday, " +
                "${lastMonday.dayOfWeek.getDisplayName(
                    java.time.format.TextStyle.FULL,
                    java.util.Locale.getDefault()
                )}")
        val result = getPreviousWeek(
            today = today,
            lastMonday.toInstant().toEpochMilli())

        println("Result: $result")
        assertEquals(11, result)
    }

    @Test
    fun `get previous week test - yesterday not included`(){
        val today = ZonedDateTime.now().with(DayOfWeek.MONDAY)
        val lastMonday = ZonedDateTime.now().with(DayOfWeek.MONDAY).minusDays(1)
        println("Last Monday: $lastMonday, " +
                "${lastMonday.dayOfWeek.getDisplayName(
                    java.time.format.TextStyle.FULL,
                    java.util.Locale.getDefault()
                )}")
        val result = getPreviousWeek(
            today = today,
            lastMonday.toInstant().toEpochMilli())

        println("Result: $result")
        assertEquals(-1, result)
    }
}