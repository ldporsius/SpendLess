package nl.codingwithlinda.authentication.pin_prompt.presentation.state

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable


data class PinPromptUiState(
    val userName: String ,
    val isLockedOut: Boolean = false,
    val retryTime: String = ""
){
    @Composable
    fun Header(){
        if (isLockedOut){
            Column {
                Text(
                    "To many failed attempts",
                    style = MaterialTheme.typography.headlineMedium
                )
                Text(
                    "Try your PIN again in $retryTime",
                    style = MaterialTheme.typography.bodyMedium
                )

            }
        }
        else
            Column {
                Text("Hello, $userName!",
                    style = MaterialTheme.typography.headlineMedium
                )
                Text("Enter your PIN",
                    style = MaterialTheme.typography.bodyMedium)
            }


    }
}