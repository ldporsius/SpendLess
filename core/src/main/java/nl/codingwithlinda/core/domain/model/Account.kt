package nl.codingwithlinda.core.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class Account(
    val id: String,
    val userName: String,
    val pin: String,
)
