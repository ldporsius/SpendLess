package nl.codingwithlinda.persistence.room.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "transactions")
data class TransactionEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val amount: String,
    val scale: Int,
    val isIncome: Boolean,
    val timestamp: Long,
    val title: String,
    val description: String,
    val category: Int,
    val accountId: String
)
