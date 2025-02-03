package nl.codingwithlinda.core.domain.encryption

import org.junit.Assert.*
import org.junit.Test

class EncryptorTest{

    val encyptor = Encryptor()

    @Test
    fun `encrypt and decrypt`(){
        encyptor.generateAESKey().let {
            println("Secret key: $it")
            println("Secret key: ${it.format}")
            println("Secret key: ${it.format.toCharArray().toString()}")

        }
    }

    @Test
    fun `Given text when encrypted and decrypted should return original text`() {
        val originalText = "Hello Kotlin AES Encryption!"
        val secretKey = encyptor.generateAESKey(256)

        val encryptedData = encyptor.aesEncrypt(originalText.toByteArray(), secretKey)
        val decryptedData = encyptor.aesDecrypt(encryptedData, secretKey)
        val decryptedText = String(decryptedData)

        println("Encrypted data: $encryptedData")
        println("Decrypted data: $decryptedData")
        println("Decrypted text: $decryptedText")

        assertEquals(originalText, decryptedText)
    }
}