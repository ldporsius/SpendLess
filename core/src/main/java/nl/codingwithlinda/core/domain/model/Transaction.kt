package nl.codingwithlinda.core.domain.model

import java.math.BigDecimal

data class Transaction(
    val amount: BigDecimal,
    val timestamp: Long,
    val title: String,
    val description: String,
    val category: Int,
)
