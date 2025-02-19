package nl.codingwithlinda.core.domain.local_cache

import kotlinx.coroutines.flow.Flow

interface DataSourceAccessFK<T, ID, FK> {

    suspend fun create(item: T): T
    suspend fun read(id: ID): T?
    suspend fun update(item: T): T
    suspend fun delete(id: ID): Boolean

    fun readAll(): Flow<List<T>>
    fun readAllFK(foreignKey: FK): Flow<List<T>>

}