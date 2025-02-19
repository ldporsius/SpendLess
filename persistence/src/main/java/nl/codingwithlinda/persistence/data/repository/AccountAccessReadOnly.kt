package nl.codingwithlinda.persistence.data.repository

import android.content.Context
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import nl.codingwithlinda.core.domain.local_cache.DataSourceAccessReadOnly
import nl.codingwithlinda.core.domain.model.Account
import nl.codingwithlinda.persistence.data.mapping.toDomain
import nl.codingwithlinda.persistence.room.SpendLessDatabase
import nl.codingwithlinda.persistence.room.dao.AccountDao

class AccountAccessReadOnly(
   private val context: Context
): DataSourceAccessReadOnly<Account, String> {

    private val db = SpendLessDatabase.getDatabase(context)
    private val accountDao: AccountDao = db.accountDao

    override fun read(id: String): Flow<Account?> {
        return accountDao.getAccountByIdFlow(id).map {
            it?.toDomain()
        }
    }

    override suspend fun getById(id: String): Account? {
       return accountDao.getAccountById(id)?.toDomain()
    }
}