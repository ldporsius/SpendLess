package nl.codingwithlinda.spendless

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import nl.codingwithlinda.spendless.ui.theme.SpendLessTheme
import nl.codingwithlinda.spendless.navigation.SpendLessApp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SpendLessTheme {
               SpendLessApp()
            }
        }
    }
}
