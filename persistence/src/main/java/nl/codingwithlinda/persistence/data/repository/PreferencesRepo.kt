package nl.codingwithlinda.persistence.data.repository

import android.content.Context
import nl.codingwithlinda.persistence.room.SpendLessDatabase

class PreferencesRepo(
    private val context: Context
) {

    val db = SpendLessDatabase.getDatabase(context)

    fun getPreferencesAccess(): PreferencesAccess{
        return PreferencesAccess(db.preferencesDao)
    }
}