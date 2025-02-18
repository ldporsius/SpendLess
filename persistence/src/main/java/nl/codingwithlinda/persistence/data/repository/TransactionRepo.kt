package nl.codingwithlinda.persistence.data.repository

import android.content.Context
import nl.codingwithlinda.persistence.room.SpendLessDatabase

class TransactionRepo(
    context: Context
) {
    val db = SpendLessDatabase.getDatabase(context)

    val access = TransactionAccess(db.transactionDao)

}