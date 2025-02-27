package nl.codingwithlinda.dashboard.categories.common.presentation

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import nl.codingwithlinda.core_ui.primaryFixed
import nl.codingwithlinda.core_ui.shared_components.CustomColoredIconButton
import nl.codingwithlinda.dashboard.categories.common.presentation.model.ExpenseCategoryUi

@Composable
fun CategoryItem(modifier: Modifier = Modifier,
                 expenseCategoryUi: ExpenseCategoryUi
) {


    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        CustomColoredIconButton(
            modifier = Modifier.padding(end = 8.dp),
            backgroundColor = primaryFixed,
            icon = {
                Text(
                    expenseCategoryUi.imageText,
                    fontSize = 24.sp
                )
            }
        )
        Text(expenseCategoryUi.expenseLabel.asString(),
            style = MaterialTheme.typography.labelMedium,
            fontWeight = FontWeight.Bold
        )
    }
}