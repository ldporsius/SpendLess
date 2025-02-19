package nl.codingwithlinda.persistence.datastore.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.core.Serializer
import androidx.datastore.dataStore
import kotlinx.serialization.SerializationException
import kotlinx.serialization.json.Json
import nl.codingwithlinda.persistence.datastore.domain.UserSession
import java.io.InputStream
import java.io.OutputStream

@Suppress("BlockingMethodInNonBlockingContext")
object UserSessionSerializer: Serializer<UserSession> {

    override val defaultValue: UserSession
        get() = UserSession()

    override suspend fun readFrom(input: InputStream): UserSession {
        return try {
            Json.decodeFromString(
                deserializer = UserSession.serializer(),
                string = input.readBytes().decodeToString()
            )
        }catch (e: SerializationException){
            e.printStackTrace()
            defaultValue
        }
    }

    override suspend fun writeTo(t: UserSession, output: OutputStream) {
        output.write(
            Json.encodeToString(
                serializer = UserSession.serializer(),
                value = t
            ).encodeToByteArray()
        )
    }

    val Context.datastore by dataStore("user_session.json", this)

}