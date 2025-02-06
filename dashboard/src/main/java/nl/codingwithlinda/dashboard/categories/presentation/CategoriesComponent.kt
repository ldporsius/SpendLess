package nl.codingwithlinda.dashboard.categories.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.key.Key.Companion.U
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import nl.codingwithlinda.core_ui.R
import nl.codingwithlinda.dashboard.categories.domain.model.ExpenseCategoryUi
import nl.codingwithlinda.dashboard.categories.presentation.mapping.expenseCategoriesToUi

@Composable
fun CategoriesComponent(
    modifier: Modifier = Modifier,
    categories: List<ExpenseCategoryUi>
) {
    val context = LocalContext.current
    Box(modifier = modifier,
        contentAlignment = Alignment.Center

    ){
        Column {
            categories.forEach {
                Text(it.imageText,
                    fontSize = 50.sp)
            }
            Text(String(Character.toChars(0x1f4b8)),
                fontSize = 100.sp
            )
        }


    }

}

@androidx.compose.ui.tooling.preview.Preview
@Composable
private fun PreviewCatImages() {

    val images = expenseCategoriesToUi(
        LocalContext.current
    )
    CategoriesComponent(
        Modifier.fillMaxSize(),
        images
    )
}