package nl.codingwithlinda.persistence.room.data.repository

import android.content.Context
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import nl.codingwithlinda.core.domain.local_cache.DataSourceAccessReadOnly
import nl.codingwithlinda.core.domain.model.PreferencesAccount
import nl.codingwithlinda.persistence.room.data.mapping.toDomain
import nl.codingwithlinda.persistence.room.SpendLessDatabase

class PreferencesAccessForAccount(
    private val context: Context
) : DataSourceAccessReadOnly<PreferencesAccount, String> {

    private val db = SpendLessDatabase.getDatabase(context)

    private val preferencesDao = db.preferencesDao

    override fun read(id: String): Flow<PreferencesAccount?> {
        return preferencesDao.readPreferencesForAccount(id).map {
            it?.toDomain()
        }
    }

    override suspend fun getById(id: String): PreferencesAccount? {
        return preferencesDao.getPreferencesForAccount(id)?.toDomain()
    }
}