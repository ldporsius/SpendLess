package nl.codingwithlinda.spendless

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import nl.codingwithlinda.core.domain.session_manager.SessionManager

class MainViewModel(
    private val sessionManager: SessionManager
): ViewModel() {

    //private val _isSessionValid = MutableStateFlow(true)

    val isSessionValid = sessionManager.isUserLoggedIn()
        .map{isUserLoggedIn ->
            val currentTime = System.currentTimeMillis()
            val isSessionValid = sessionManager.isSessionValid(currentTime)

            isSessionValid && isUserLoggedIn
        }
        .stateIn(viewModelScope, SharingStarted.Eagerly, true)

}