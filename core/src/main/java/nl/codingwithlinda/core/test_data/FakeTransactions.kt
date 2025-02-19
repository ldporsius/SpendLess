package nl.codingwithlinda.core.test_data

import android.annotation.SuppressLint
import nl.codingwithlinda.core.domain.model.Transaction
import java.math.BigDecimal
import java.time.ZonedDateTime
import kotlin.random.Random

@SuppressLint("NewApi")
fun fakeTransactions(
    accountId: String
)= List(100) {

    val amount = Random.nextDouble(-100.00, 1000.0)
    val category = Random.nextInt(0, 5)
    Transaction(
        amount = BigDecimal(amount.toString()),
        timestamp = ZonedDateTime.now().minusHours(it.toLong()).toEpochSecond() * 1000,
        title = "Adobe yearly",
        description = "Enjoyed a coffee at Starbucks with Jem and Jim",
        category = category,
        accountId = accountId
    )
}