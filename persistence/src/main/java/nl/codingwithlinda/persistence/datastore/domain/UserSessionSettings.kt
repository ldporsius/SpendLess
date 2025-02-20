package nl.codingwithlinda.persistence.datastore.domain

import kotlinx.serialization.Serializable

@Serializable
data class UserSessionSettings(
    val userId: String = "",
    val sessionDuration: Long = 0L,
    val sessionStartTime: Long = 0L
)
