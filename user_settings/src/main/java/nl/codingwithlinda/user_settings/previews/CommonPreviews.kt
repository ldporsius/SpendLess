package nl.codingwithlinda.user_settings.previews

import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import nl.codingwithlinda.core_ui.SpendLessTheme
import nl.codingwithlinda.core_ui.shared_components.CustomColoredIconButton
import nl.codingwithlinda.user_settings.main.presentation.UserSettingsRoot

@androidx.compose.ui.tooling.preview.Preview
@Composable
private fun CustomColoredIconButtonPreview() {

    SpendLessTheme {
        CustomColoredIconButton(
            modifier = androidx.compose.ui.Modifier,
            backgroundColor = androidx.compose.material3.MaterialTheme.colorScheme.primary,
            icon = {
                Icon(painter = painterResource(nl.codingwithlinda.core_ui.R.drawable.logout),
                    contentDescription = "Logout",
                    tint = androidx.compose.material3.MaterialTheme.colorScheme.onPrimary
                )
            }
        )
    }
}


@androidx.compose.ui.tooling.preview.Preview
@Composable
private fun UserSettingsRootPreview() {

    SpendLessTheme {
        UserSettingsRoot(
            onNavBack = {},
            onAction = {}
        )

    }
}