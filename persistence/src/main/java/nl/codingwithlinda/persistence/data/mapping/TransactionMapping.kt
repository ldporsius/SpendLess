package nl.codingwithlinda.persistence.data.mapping

import nl.codingwithlinda.core.domain.model.Transaction
import nl.codingwithlinda.persistence.model.TransactionEntity
import java.math.BigDecimal

fun TransactionEntity.toDomain(): Transaction{

    val amount = BigDecimal(this.amount).movePointLeft(this.scale)

    return Transaction(
        amount = amount,
        timestamp = this.timestamp,
        title = this.title,
        description = this.description,
        category = this.category
    )
}

fun Transaction.toEntity(): TransactionEntity{
    return TransactionEntity(
        id = 0,
        amount = this.amount.unscaledValue().toString(),
        scale = this.amount.scale(),
        isIncome = this.amount > BigDecimal.ZERO,
        timestamp = this.timestamp,
        title = this.title,
        description = this.description,
        category = this.category,
    )
}