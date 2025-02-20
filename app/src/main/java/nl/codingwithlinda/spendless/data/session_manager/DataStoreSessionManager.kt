package nl.codingwithlinda.spendless.data.session_manager

import android.annotation.SuppressLint
import android.content.Context
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import nl.codingwithlinda.authentication.core.data.session_manager.finite_states.SessionLockedState
import nl.codingwithlinda.authentication.core.data.session_manager.finite_states.SessionUnlockedState
import nl.codingwithlinda.authentication.core.domain.error.SessionError
import nl.codingwithlinda.authentication.core.domain.session_manager.SessionState
import nl.codingwithlinda.authentication.login.data.LoginValidator
import nl.codingwithlinda.core.domain.error.RootError
import nl.codingwithlinda.core.domain.local_cache.DataSourceAccessReadOnly
import nl.codingwithlinda.core.domain.model.Account
import nl.codingwithlinda.core.domain.result.SpendResult
import nl.codingwithlinda.core.domain.session_manager.SessionManager
import nl.codingwithlinda.persistence.datastore.data.UserSessionSerializer.datastore
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import kotlin.time.Duration.Companion.milliseconds

class DataStoreSessionManager(
    context: Context,
    loginValidator:LoginValidator,
    accountAccess: DataSourceAccessReadOnly<Account, String>,

): SessionManager {

    companion object{
        const val DEFAULT_SESSION_DURATION = 60_000 * 5L
        const val MAX_NUMBER_LOGIN_ATTEMPTS = 3
    }

    private var numberLoginAttempts = 0
    private val sessionLockedState = SessionLockedState(this)
    private val sessionUnlockedState = SessionUnlockedState(
        this,
        loginValidator = loginValidator,
        accountAccess = accountAccess
    )

    private val datastore = context.datastore

    override suspend fun setSessionDuration(duration: Long) {
        datastore.updateData {
            it.copy(
                sessionDuration = duration
            )
        }
    }
    override suspend fun getSessionDuration(): Long {
        return datastore.data.map {
            it.sessionDuration
        }.firstOrNull() ?: DEFAULT_SESSION_DURATION
    }

    override suspend fun setUserId(userId: String) {
        datastore.updateData {
            it.copy(
                userId = userId
            )
        }
    }

    override fun getUserId(): Flow<String?> {
        return datastore.data.map {
            it.userId
        }
    }

    override fun isUserLoggedIn(): Flow<Boolean> {
        return datastore.data.map {
            it.userId.isNotEmpty()
        }
    }

    override suspend fun startSession() {
       datastore.updateData {
           it.copy(
               sessionStartTime = System.currentTimeMillis()
           )
       }
    }
    override suspend fun endSession() {
        datastore.updateData {
            it.copy(
                userId = "",
                sessionStartTime = 0L
            )
        }
    }

    var state: SessionState = sessionLockedState

    override suspend fun lockOutUser() {
        state = sessionLockedState
    }

    override suspend fun unlockUser() {
        state = sessionUnlockedState
    }

    override suspend fun login(pin: String, onResult: (SpendResult<Account?, RootError>) -> Unit) {
        val accountId = datastore.data.map {
            it.userId
        }.firstOrNull()

        if (accountId == null){
            onResult( SpendResult.Failure(SessionError.NoAccountError))
            return
        }

        state.login(accountId, pin).also {
            when(it){
                is SpendResult.Failure -> {
                    numberLoginAttempts ++
                    if (numberLoginAttempts >= MAX_NUMBER_LOGIN_ATTEMPTS) {
                        state = sessionLockedState
                    }
                }
                is SpendResult.Success -> {
                    numberLoginAttempts = 0
                    state = sessionUnlockedState

                }
            }
        }.also {res ->

            when(res){
                is SpendResult.Failure -> {
                    onResult( SpendResult.Failure(res.error))
                }
                is SpendResult.Success -> {
                    onResult( SpendResult.Success(res.data))
                }
            }

        }
    }

    @SuppressLint("NewApi")
    override suspend fun isSessionValid(currentTime: Long): Boolean {
        val durationSettings = getSessionDuration()
        println("SESSION MANAGER DURATION SETTINGS: ${durationSettings.milliseconds}")

        val startTime = datastore.data.map {
            it.sessionStartTime
        }.firstOrNull() ?: (currentTime + durationSettings)

        val localDateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(startTime), ZoneId.systemDefault())
        println("SESSION MANAGER START TIME: ${localDateTime.toString()}")

        val expiritionTime = localDateTime.plusMinutes(durationSettings.milliseconds.inWholeMinutes)
        println("SESSION MANAGER EXPIRATION TIME: ${expiritionTime.toString()}")

        return  currentTime - startTime < durationSettings
    }
}