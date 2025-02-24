package nl.codingwithlinda.user_settings.security.presentation.mapping

import nl.codingwithlinda.core.domain.model.LockedOutDuration
import nl.codingwithlinda.core.domain.model.SessionDuration
import nl.codingwithlinda.core_ui.util.UiText

fun SessionDuration.toUi(): UiText{
    return when(this){
        SessionDuration.FIVE_MINUTES -> UiText.DynamicText("5 min")
        SessionDuration.FIFTEEN_MINUTES -> UiText.DynamicText("15 min")
        SessionDuration.THIRTY_MINUTES -> UiText.DynamicText("30 min")
        SessionDuration.ONE_HOUR -> UiText.DynamicText("1 hour")
    }
}

fun LockedOutDuration.toUi(): UiText{
    return when(this){
        LockedOutDuration.FIFTEEN_SECONDS -> UiText.DynamicText("15 sec")
        LockedOutDuration.THIRTY_SECONDS -> UiText.DynamicText("30 sec")
        LockedOutDuration.ONE_MINUTE -> UiText.DynamicText("1 min")
        LockedOutDuration.FIVE_MINUTES -> UiText.DynamicText("5 min")
    }
}