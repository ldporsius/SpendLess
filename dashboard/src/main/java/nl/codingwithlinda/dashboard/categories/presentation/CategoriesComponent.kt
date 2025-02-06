package nl.codingwithlinda.dashboard.categories.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.sp

@Composable
fun CategoriesComponent(
    modifier: Modifier = Modifier,
    categorieImages: List<String>
) {
    Box(modifier = modifier,
        contentAlignment = Alignment.Center

    ){
        Column {
            categorieImages.forEach {
                Text(it,
                    fontSize = 100.sp)
            }
        }


    }

}

@androidx.compose.ui.tooling.preview.Preview
@Composable
private fun PreviewCatImages() {

    val images = CategoryImageProvider(
        LocalContext.current
    ).categoryImages()
    CategoriesComponent(
        Modifier.fillMaxSize(),
        images
    )
}