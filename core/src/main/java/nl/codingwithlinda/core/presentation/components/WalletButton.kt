package nl.codingwithlinda.core.presentation.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import nl.codingwithlinda.core.R

@Composable
fun WalletButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {

    FilledIconButton(
        onClick = { onClick() },
        modifier = modifier .size(64.dp),
        shape = RoundedCornerShape(16.dp),
        colors = IconButtonDefaults.filledIconButtonColors(
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = MaterialTheme.colorScheme.onPrimary,
        )
    ){

        Icon(
          painter = painterResource(id = nl.codingwithlinda.core_ui.R.drawable.account_balance_wallet),
            contentDescription = null,
            modifier = Modifier
                .padding(10.dp)
                .size(30.dp)
        )

    }
}