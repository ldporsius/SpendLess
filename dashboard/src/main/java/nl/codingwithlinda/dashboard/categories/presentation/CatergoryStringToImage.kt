package nl.codingwithlinda.dashboard.categories.presentation

import android.content.Context

class CategoryImageProvider(
private val context: Context
) {
    fun categoryImages() = listOf<String>(

        String(Character.toChars(0x1F3E0)),
        context.getString(nl.codingwithlinda.core_ui.R.string.pizza),
        context.getString(nl.codingwithlinda.core_ui.R.string.necktie)
    )

}