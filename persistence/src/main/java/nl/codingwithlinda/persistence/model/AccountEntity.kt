package nl.codingwithlinda.persistence.model

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "accounts",
    indices = [Index("userName", unique = true)]
    )
data class AccountEntity(
    @PrimaryKey(autoGenerate = false)
    val id: String,
    val userName: String,
    val pin: String
)
