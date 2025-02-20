package nl.codingwithlinda.spendless

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import nl.codingwithlinda.core.domain.session_manager.SessionManager

class MainViewModel(
    private val sessionManager: SessionManager
): ViewModel() {

    private val _isSessionValid = MutableStateFlow(true)

    val isSessionValid = _isSessionValid
        .onStart {
            val currentTime = System.currentTimeMillis()
            _isSessionValid.value = sessionManager.isSessionValid(currentTime)
        }.combine(sessionManager.isUserLoggedIn()){
            isSessionValid, isUserLoggedIn ->
            isSessionValid && isUserLoggedIn
        }
        .stateIn(viewModelScope, SharingStarted.Eagerly, _isSessionValid.value)


}