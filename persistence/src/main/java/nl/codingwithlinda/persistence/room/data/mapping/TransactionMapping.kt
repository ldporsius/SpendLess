package nl.codingwithlinda.persistence.room.data.mapping

import nl.codingwithlinda.core.domain.model.Transaction
import nl.codingwithlinda.persistence.room.domain.model.TransactionEntity
import java.math.BigDecimal

fun TransactionEntity.toDomain(): Transaction{

    val amount = BigDecimal(this.amount).movePointLeft(this.scale)

    return Transaction(
        id = this.id,
        amount = amount,
        timestamp = this.timestamp,
        title = this.title,
        description = this.description,
        category = this.category,
        accountId = this.accountId
    )
}

fun Transaction.toEntity(): TransactionEntity {
    return TransactionEntity(
        id = this.id,
        amount = this.amount.unscaledValue().toString(),
        scale = this.amount.scale(),
        isIncome = this.amount > BigDecimal.ZERO,
        timestamp = this.timestamp,
        title = this.title,
        description = this.description,
        category = this.category,
        accountId = this.accountId

    )
}