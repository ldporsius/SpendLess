package nl.codingwithlinda.core.data.dto

import nl.codingwithlinda.core.data.util.stringToBigDecimal
import nl.codingwithlinda.core.domain.model.Transaction

fun TransactionDto.toDomain(): Transaction{
    val amountBD = stringToBigDecimal(amount)
    return Transaction(
        amount = amountBD,
        timestamp = timestamp,
        title = title,
        description = description,
        category = category,
        accountId = accountId
    )
}