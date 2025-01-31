package nl.codingwithlinda.authentication.registration.presentation

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import nl.codingwithlinda.authentication.registration.presentation.state.RegisterUserViewState

class RegisterUserViewModel: ViewModel() {

    private val _uiState = MutableStateFlow(RegisterUserViewState())
    val uiState = _uiState.asStateFlow()
}