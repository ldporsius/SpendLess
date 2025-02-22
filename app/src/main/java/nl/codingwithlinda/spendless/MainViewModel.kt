package nl.codingwithlinda.spendless

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import nl.codingwithlinda.core.domain.session_manager.AuthenticationManager
import nl.codingwithlinda.core.domain.session_manager.SessionManager
import nl.codingwithlinda.spendless.data.authentication_manager.AppAuthenticationManager

class MainViewModel(
    private val authenticationManager: AuthenticationManager
): ViewModel() {


    suspend fun isSessionValid() = authenticationManager.handleEvent()

}