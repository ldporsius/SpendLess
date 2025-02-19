package nl.codingwithlinda.persistence.room.data.repository

import android.content.Context
import nl.codingwithlinda.persistence.room.SpendLessDatabase

class AccountRepo(
    context: Context
) {
    private val db = SpendLessDatabase.getDatabase(context)

    fun getAccountAccess(): AccountAccess {
        return AccountAccess(db.accountDao)
    }
}