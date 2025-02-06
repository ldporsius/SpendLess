package nl.codingwithlinda.core.data

import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Test

class AccountFactoryTest{

    val factory = AccountFactory()
    val account = factory.create("linda", "12345")

    @Test
    fun testAccountFactory_encryptedIsDecrypted() = runBlocking {
        val encrypted = factory.encrypt(account)
        println("encrypted account: $encrypted")
        val decrypted = factory.decrypt(encrypted)
        println("decrypted account: $decrypted")

        assertTrue(account.pin == decrypted.pin)
    }

    @Test
    fun testAccountFactory_noCrashInvalidPin() = runBlocking {
        val account = factory.create("linda", "")

        val encrypted = factory.encrypt(account)
        println("encrypted account: $encrypted")
        val decrypted = factory.decrypt(encrypted)
        println("decrypted account: $decrypted")

        assertTrue(account.pin == decrypted.pin)
    }
}