package nl.codingwithlinda.dashboard.categories.common.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import nl.codingwithlinda.dashboard.categories.common.presentation.model.ExpenseCategoryUi
import nl.codingwithlinda.dashboard.categories.common.presentation.mapping.expenseCategoriesToUi

@Composable
fun CategoriesComponent(
    modifier: Modifier = Modifier,
    categories: List<ExpenseCategoryUi>
) {

    Box(modifier = modifier,
        contentAlignment = Alignment.TopStart

    ){
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            categories.forEach {
                CategoryItem(
                    modifier = Modifier.fillMaxWidth(),
                    expenseCategoryUi = it
                )
            }
        }


    }

}

