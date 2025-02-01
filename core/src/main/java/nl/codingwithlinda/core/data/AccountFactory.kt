package nl.codingwithlinda.core.data

import nl.codingwithlinda.core.domain.model.Account

class AccountFactory {

    fun create(userName: String, pin: String): Account {
        return Account(
            userName = userName,
            pin = pin
        )
    }
}