package nl.codingwithlinda.persistence.room.domain.model

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey


@Entity(tableName = "preferences",
    indices = [Index(value = ["accountId"], unique = true)]
)
data class PreferencesEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val preferencesJson: String,
    val accountId: String
)
