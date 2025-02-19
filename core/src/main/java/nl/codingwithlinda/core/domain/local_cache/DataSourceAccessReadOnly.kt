package nl.codingwithlinda.core.domain.local_cache

import kotlinx.coroutines.flow.Flow

interface DataSourceAccessReadOnly<T, ID> {
    fun read(id: ID): Flow<T?>
    suspend fun getById(id: ID): T?
}