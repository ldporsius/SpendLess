package nl.codingwithlinda.persistence.data.mapping

import nl.codingwithlinda.core.domain.model.Account
import nl.codingwithlinda.persistence.model.AccountEntity

fun Account.toEntity(): AccountEntity {
    return AccountEntity(
        id = id,
        userName = userName,
        pin = pin
    )
}

fun AccountEntity.toDomain(): Account{
    return Account(
        id = id,
        userName = userName,
        pin = pin
    )
}