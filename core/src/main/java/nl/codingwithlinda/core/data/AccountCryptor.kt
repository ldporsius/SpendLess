package nl.codingwithlinda.core.data

import android.os.Build
import nl.codingwithlinda.core.domain.encryption.Crypto
import nl.codingwithlinda.core.domain.model.Account
import java.util.Base64

class AccountCryptor {

    fun encrypt(account: Account): Account {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O)
            return account

        val encryptedPin = Crypto.encrypt(account.pin.toByteArray())
        val encryptedPinBase64 = Base64.getEncoder().encodeToString(encryptedPin)
        return account.copy(
            userName = account.userName,
            pin = encryptedPinBase64
        )
    } fun decrypt(account: Account): Account{
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O)
            return account

        if (account.pin.isBlank() || account.pin.isEmpty()) return account

        val encryptedPin = account.pin
        try {
            val decryptedPinBase64 = Base64.getDecoder().decode(encryptedPin)
            val decryptedPinBytes = Crypto.decrypt(decryptedPinBase64)
            val decryptedPinString = decryptedPinBytes.decodeToString()
            return account.copy(
                userName = account.userName,
                pin = decryptedPinString
            )
        }catch (e: Exception){
            return account
        }

    }
}