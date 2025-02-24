package nl.codingwithlinda.user_settings.security.presentation

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import nl.codingwithlinda.user_settings.security.presentation.state.SecurityAction
import nl.codingwithlinda.user_settings.security.presentation.state.SecurityUiState

class SecuritySettingsViewModel: ViewModel() {

    private val _uiState = MutableStateFlow(SecurityUiState())
    val uiState = _uiState.asStateFlow()

    fun handleAction(action: SecurityAction) {
        when(action) {
            is SecurityAction.SelectSessionDuration -> {
               _uiState.update {
                   it.copy(
                       selectedSessionDuration = action.duration
                   )
               }
            }

            is SecurityAction.SelectLockedOutDuration -> {
                _uiState.update {
                    it.copy(
                        selectedLockedOutDuration = action.duration
                    )
                }
            }
        }
    }
}