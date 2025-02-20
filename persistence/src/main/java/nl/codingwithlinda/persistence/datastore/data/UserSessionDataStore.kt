package nl.codingwithlinda.persistence.datastore.data

import android.content.Context
import androidx.datastore.core.Serializer
import androidx.datastore.dataStore
import kotlinx.serialization.SerializationException
import kotlinx.serialization.json.Json
import nl.codingwithlinda.persistence.datastore.domain.UserSessionSettings
import java.io.InputStream
import java.io.OutputStream

@Suppress("BlockingMethodInNonBlockingContext")
object UserSessionSerializer: Serializer<UserSessionSettings> {

    override val defaultValue: UserSessionSettings
        get() = UserSessionSettings()

    override suspend fun readFrom(input: InputStream): UserSessionSettings {
        return try {
            Json.decodeFromString(
                deserializer = UserSessionSettings.serializer(),
                string = input.readBytes().decodeToString()
            )
        }catch (e: SerializationException){
            e.printStackTrace()
            defaultValue
        }
    }

    override suspend fun writeTo(t: UserSessionSettings, output: OutputStream) {
        output.write(
            Json.encodeToString(
                serializer = UserSessionSettings.serializer(),
                value = t
            ).encodeToByteArray()
        )
    }

    val Context.datastore by dataStore("user_session.json", this)

}