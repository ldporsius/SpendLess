package nl.codingwithlinda.dashboard.categories.common.presentation.mapping

import android.content.Context
import nl.codingwithlinda.core.domain.model.ExpenseCategory
import nl.codingwithlinda.core_ui.R.string.*


    fun categoryImages(context: Context) = mapOf(
        ExpenseCategory.CLOTHING_ACCESSORIES to
                context.getString(necktie),
        ExpenseCategory.EDUCATION to
                context.getString(graduation_cap),
        ExpenseCategory.ENTERTAINMENT to
                context.getString(laptop),
        ExpenseCategory.FOOD_GROCERIES to
                context.getString(pizza),
        ExpenseCategory.HEALTH_WELLNESS to
                context.getString(red_heart),
        ExpenseCategory.HOME to
                context.getString(house)
    )

