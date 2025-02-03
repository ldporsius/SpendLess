package nl.codingwithlinda.persistence.data.mapping

import nl.codingwithlinda.core.domain.model.Account
import nl.codingwithlinda.persistence.model.AccountEntity

fun Account.toEntity(): AccountEntity {
    return AccountEntity(
        userName = userName,
        pin = pin
    )
}

fun AccountEntity.toDomain(): Account{
    return Account(
        userName = userName,
        pin = pin
    )
}