package nl.codingwithlinda.persistence.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class AccountEntity(
    @PrimaryKey(autoGenerate = false)
    val userName: String,
    val pin: String
)
