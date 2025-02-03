package nl.codingwithlinda.persistence.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import nl.codingwithlinda.persistence.model.AccountEntity


@Dao
interface AccountDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun createAccount(account: AccountEntity)

    @Query("SELECT * FROM accounts")
    fun readAccounts(): Flow<List<AccountEntity>>

    @Query("SELECT * FROM accounts WHERE userName = :userName")
    fun getAccountForUser(userName: String): AccountEntity?

    @Query("SELECT * FROM accounts WHERE userName = :userName AND pin = :pin")
    fun getAccountForUserAndPIN(userName: String, pin: String): AccountEntity?
}