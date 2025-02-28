package nl.codingwithlinda.authentication.core.data

import kotlinx.coroutines.runBlocking
import nl.codingwithlinda.core.data.encryption.AccountCryptor
import org.junit.Assert.*
import org.junit.Test

class AccountFactoryTest{

    val factory = nl.codingwithlinda.authentication.core.data.AccountFactory()
    val account = factory.create("linda", "12345")
    val cryptor = AccountCryptor()

    @Test
    fun testAccountFactory_encryptedIsDecrypted() = runBlocking {
        val encrypted = cryptor.encrypt(account)
        println("encrypted account: $encrypted")
        val decrypted = cryptor.decrypt(encrypted)
        println("decrypted account: $decrypted")

        assertTrue(account.pin == decrypted.pin)
    }

    @Test
    fun testAccountFactory_noCrashInvalidPin() = runBlocking {
        val account = factory.create("linda", "")

        val encrypted = cryptor.encrypt(account)
        println("encrypted account: $encrypted")
        val decrypted = cryptor.decrypt(encrypted)
        println("decrypted account: $decrypted")

        assertTrue(account.pin == decrypted.pin)
    }
}