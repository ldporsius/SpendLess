package nl.codingwithlinda.core.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class TransactionDto(
    val id: Long,
    val amount: String,
    val timestamp: Long,
    val title: String,
    val description: String,
    val category: Int,
    val accountId: String
)
