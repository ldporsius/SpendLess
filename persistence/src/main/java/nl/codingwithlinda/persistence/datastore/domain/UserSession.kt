package nl.codingwithlinda.persistence.datastore.domain

import kotlinx.serialization.Serializable

@Serializable
data class UserSession(
    val userId: String = "",
)
