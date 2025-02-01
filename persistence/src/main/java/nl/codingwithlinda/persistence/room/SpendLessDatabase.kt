package nl.codingwithlinda.persistence.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import nl.codingwithlinda.persistence.model.AccountEntity
import nl.codingwithlinda.persistence.room.dao.AccountDao

@Database(
    version = 1,
    entities = [AccountEntity::class]
)
abstract class SpendLessDatabase: RoomDatabase(){

    abstract val accountDao: AccountDao

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