package nl.codingwithlinda.core.test_data

import nl.codingwithlinda.core.domain.model.ExpenseCategory
import nl.codingwithlinda.core.domain.model.Transaction
import java.math.BigDecimal

fun fakeTransactions()= List(100) {
    Transaction(
        amount = BigDecimal.TEN,
        timestamp = System.currentTimeMillis(),
        title = "title",
        description = "description",
        category = ExpenseCategory.FOOD_GROCERIES.identifier,
    )
}