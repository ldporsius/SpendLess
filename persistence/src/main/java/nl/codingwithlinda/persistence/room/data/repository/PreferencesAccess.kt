package nl.codingwithlinda.persistence.room.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import nl.codingwithlinda.core.domain.local_cache.DataSourceAccess
import nl.codingwithlinda.core.domain.local_cache.DataSourceAccessFK
import nl.codingwithlinda.core.domain.model.Preferences
import nl.codingwithlinda.core.domain.model.PreferencesAccount
import nl.codingwithlinda.persistence.room.data.mapping.toDomain
import nl.codingwithlinda.persistence.room.data.mapping.toEntity
import nl.codingwithlinda.persistence.room.dao.PreferencesDao

class PreferencesAccess(
    private val preferencesDao: PreferencesDao
): DataSourceAccess<PreferencesAccount, String> {
    override suspend fun create(item: PreferencesAccount): PreferencesAccount {
        preferencesDao.createPreferences(item.toEntity())
        return item
    }

    override suspend fun update(item: PreferencesAccount): PreferencesAccount {
        preferencesDao.createPreferences(item.toEntity())
        return item
    }

    override fun readAll(): Flow<List<PreferencesAccount>> {
        return preferencesDao.readPreferences().map { entities ->
            entities.map {
                it.toDomain()
            }
        }
    }

    override suspend fun delete(id: String): Boolean {
        return false
    }

    override suspend fun read(id: String): PreferencesAccount? {
        return preferencesDao.getPreferencesForAccount(id)?.toDomain()
    }
}