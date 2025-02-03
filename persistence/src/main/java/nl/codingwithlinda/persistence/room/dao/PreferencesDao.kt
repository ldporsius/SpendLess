package nl.codingwithlinda.persistence.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import nl.codingwithlinda.persistence.model.PreferencesEntity

@Dao
interface PreferencesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun createPreferences(preferences: PreferencesEntity)

    @Query("SELECT * FROM preferences LIMIT 1")
    fun readPreferences(): Flow<List<PreferencesEntity>>

}