package nl.codingwithlinda.persistence.room.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "preferences")
data class PreferencesEntity(
    @PrimaryKey(autoGenerate = false) val id: Long = 0,
    val preferencesJson: String,
    val accountId: String
)
