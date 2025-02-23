package nl.codingwithlinda.persistence.datastore.domain

import kotlinx.serialization.Serializable

@Serializable
data class UserSessionSettings(
    val accountId: String = "",
    val sessionDuration: Long = 60_000 * 1,
    val sessionStartTime: Long = System.currentTimeMillis(),
    val numberLoginAttempts: Int = 0,
    val lockedOutStartTime: Long = 0L
)
