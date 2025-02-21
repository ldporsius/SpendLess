package nl.codingwithlinda.authentication.pin_prompt.presentation.state

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable


data class PinPromptUiState(
    val userName: String ,
    val isLockedOut: Boolean = false,
){
    @Composable
    fun Header(){
        if (isLockedOut){
            Text("Your session has been locked out")
        }
        else
            Text("Hello, $userName!")

    }
}