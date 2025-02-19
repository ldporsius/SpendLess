package nl.codingwithlinda.persistence.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import nl.codingwithlinda.persistence.room.domain.model.PreferencesEntity

@Dao
interface PreferencesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun createPreferences(preferences: PreferencesEntity)

    @Query("SELECT * FROM preferences WHERE accountId = :accountId LIMIT 1")
    fun readPreferencesForAccount(accountId: String): Flow<PreferencesEntity?>

    @Query("SELECT * FROM preferences WHERE accountId = :accountId LIMIT 1")
    suspend fun getPreferencesForAccount(accountId: String): PreferencesEntity?

    @Query("SELECT * FROM preferences")
    fun readPreferences(): Flow<List<PreferencesEntity>>


}