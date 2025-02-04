package nl.codingwithlinda.core.domain.encryption

import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Test

class CryptoTest{

    val sut = "Hello World"

    @Test
    fun `test cryptography`(): Unit = runBlocking {
        val encrypted = Crypto.encrypt(sut.encodeToByteArray())

        println("Encrypted: $encrypted")

        val decrypted = Crypto.decrypt(encrypted)

        println("Decrypted: $decrypted")

        assertEquals(sut, decrypted.decodeToString())

    }
}