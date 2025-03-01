package nl.codingwithlinda.core.test_data

import nl.codingwithlinda.core.domain.encryption.Cryptor
import nl.codingwithlinda.core.domain.model.Account

class FakeCryptor: Cryptor<Account> {
    override fun encrypt(obj: Account): Account {
        return obj
    }

    override fun decrypt(obj: Account): Account {
       return obj
    }
}