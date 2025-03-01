package nl.codingwithlinda.core.test_data.test_data_generators

import nl.codingwithlinda.core.domain.model.Account

fun fakeAccount(accountId: String): Account{
    return Account(
        id = accountId,
        userName = "linda",
        pin = "12345",
    )
}