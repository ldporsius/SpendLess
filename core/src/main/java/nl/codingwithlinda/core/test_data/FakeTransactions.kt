package nl.codingwithlinda.core.test_data

import android.annotation.SuppressLint
import nl.codingwithlinda.core.domain.model.ExpenseCategory
import nl.codingwithlinda.core.domain.model.Transaction
import java.math.BigDecimal
import java.time.ZonedDateTime

@SuppressLint("NewApi")
fun fakeTransactions()= List(100) {
    Transaction(
        amount = BigDecimal.TEN,
        timestamp = ZonedDateTime.now().minusDays(it.toLong()).toEpochSecond() *1000,
        title = "Adobe yearly",
        description = "Enjoyed a coffee at Starbucks with Jem and Jim",
        category = ExpenseCategory.FOOD_GROCERIES.identifier,
    )
}