package nl.codingwithlinda.spendless.navigation.core.destinations

import kotlinx.serialization.Serializable

@Serializable
sealed interface DashboardNavRoute : NavRoute {

    @Serializable
    data object DashboardRoot : DashboardNavRoute

    @Serializable
    data object AllTransactionsNavRoute : DashboardNavRoute

    @Serializable
    data object CreateTransactionNavRoute : DashboardNavRoute


}