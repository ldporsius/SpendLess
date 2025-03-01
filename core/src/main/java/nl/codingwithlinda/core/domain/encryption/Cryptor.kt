package nl.codingwithlinda.core.domain.encryption

interface Cryptor<T> {

    fun encrypt(obj: T): T
    fun decrypt(obj: T): T

}