package nl.codingwithlinda.user_settings.security.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import nl.codingwithlinda.core.domain.model.LockedOutDuration
import nl.codingwithlinda.core.domain.model.SessionDuration
import nl.codingwithlinda.user_settings.security.domain.usecase.GetSecuritySettingsUseCase
import nl.codingwithlinda.user_settings.security.domain.usecase.SaveSecuritySettingsUseCase
import nl.codingwithlinda.user_settings.security.presentation.state.SecurityAction
import nl.codingwithlinda.user_settings.security.presentation.state.SecurityUiState

class SecuritySettingsViewModel(
    private val getSecuritySettingsUseCase: GetSecuritySettingsUseCase,
    private val saveSecuritySettingsUseCase: SaveSecuritySettingsUseCase,
    private val onSaved: () -> Unit
): ViewModel() {

    private val _sessionDuration = MutableStateFlow(SessionDuration.FIVE_MINUTES)
    private val _lockOutDuration = MutableStateFlow(LockedOutDuration.FIFTEEN_SECONDS)

    private val _uiState = MutableStateFlow(SecurityUiState())
    val uiState = combine(_sessionDuration, _lockOutDuration, _uiState) {sessionDuration, lockOutDuration, uiState ->
        uiState.copy(
            selectedSessionDuration = sessionDuration,
            selectedLockedOutDuration = lockOutDuration
        )
    }.onStart {
        val (sess, lock) = getSecuritySettingsUseCase.getSettingsForLoggedInUser()
        _sessionDuration.update {
            sess
        }
        _lockOutDuration.update {
            lock
        }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), _uiState.value)

    fun handleAction(action: SecurityAction) {
        when(action) {
            is SecurityAction.SelectSessionDuration -> {
              _sessionDuration.update {
                  action.duration
              }
            }

            is SecurityAction.SelectLockedOutDuration -> {
               _lockOutDuration.update {
                   action.duration
               }
            }

            is SecurityAction.Save -> {
                viewModelScope.launch {
                    saveSecuritySettingsUseCase.save(
                        _sessionDuration.value,
                        _lockOutDuration.value
                    )
                    onSaved()
                }
            }

        }
    }
}