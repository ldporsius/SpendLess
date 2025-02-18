package nl.codingwithlinda.core.test_data

import nl.codingwithlinda.core.domain.model.ExpenseCategory
import nl.codingwithlinda.core.domain.model.Transaction
import java.math.BigDecimal

fun fakeTransactions()= List(100) {
    Transaction(
        amount = BigDecimal.TEN,
        timestamp = System.currentTimeMillis(),
        title = "Adobe yearly",
        description = "Enjoyed a coffee at Starbucks with Jem and Jim",
        category = ExpenseCategory.FOOD_GROCERIES.identifier,
    )
}