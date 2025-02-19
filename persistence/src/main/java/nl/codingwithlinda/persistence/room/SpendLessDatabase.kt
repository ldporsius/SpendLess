package nl.codingwithlinda.persistence.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import nl.codingwithlinda.persistence.room.domain.model.AccountEntity
import nl.codingwithlinda.persistence.room.domain.model.PreferencesEntity
import nl.codingwithlinda.persistence.room.domain.model.TransactionEntity
import nl.codingwithlinda.persistence.room.dao.AccountDao
import nl.codingwithlinda.persistence.room.dao.PreferencesDao
import nl.codingwithlinda.persistence.room.dao.TransactionDao

@Database(
    version = 4,
    entities = [AccountEntity::class, PreferencesEntity::class, TransactionEntity::class]
)
abstract class SpendLessDatabase: RoomDatabase(){

    abstract val accountDao: AccountDao
    abstract val preferencesDao: PreferencesDao
    abstract val transactionDao: TransactionDao

    companion object {
        @Volatile
        private var INSTANCE: SpendLessDatabase? = null

        fun getDatabase(context: Context): SpendLessDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    SpendLessDatabase::class.java,
                    "spendless_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}