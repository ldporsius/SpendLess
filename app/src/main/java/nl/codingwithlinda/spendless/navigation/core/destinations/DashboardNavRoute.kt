package nl.codingwithlinda.spendless.navigation.core.destinations

import kotlinx.serialization.Serializable
import nl.codingwithlinda.core.data.dto.TransactionDto

@Serializable
sealed interface DashboardNavRoute : NavRoute {

    @Serializable
    data object DashboardRoot : DashboardNavRoute

    @Serializable
    data object AllTransactionsNavRoute : DashboardNavRoute

    @Serializable
    data class SaveTransactionNavRoute(
        val transaction: TransactionDto
    ) : DashboardNavRoute

    @Serializable
    data object CreateTransactionNavRoute : DashboardNavRoute


}