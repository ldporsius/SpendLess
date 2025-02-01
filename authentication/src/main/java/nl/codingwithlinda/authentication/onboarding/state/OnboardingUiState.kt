package nl.codingwithlinda.authentication.onboarding.state

import nl.codingwithlinda.core.domain.model.Preferences

data class OnboardingUiState(
    val exampleFormattedText: String,
    val preferences: Preferences
)
