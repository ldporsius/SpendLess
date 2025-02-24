package nl.codingwithlinda.authentication.core.data

import nl.codingwithlinda.core.domain.model.Account
import java.util.UUID

class AccountFactory {

    fun create(userName: String, pin: String): Account {
        return Account(
            id = UUID.randomUUID().toString(),
            userName = userName,
            pin = pin
        )
    }
}