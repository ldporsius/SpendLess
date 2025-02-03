package nl.codingwithlinda.spendless

import androidx.lifecycle.ViewModel
import nl.codingwithlinda.core.domain.session_manager.SessionManager

class MainViewModel(
    private val sessionManager: SessionManager
): ViewModel() {

     val isUserLoggedIn = sessionManager.isUserLoggedIn()
}