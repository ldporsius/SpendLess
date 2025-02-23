package nl.codingwithlinda.spendless.navigation.dashboard

import kotlinx.serialization.Serializable
import nl.codingwithlinda.spendless.navigation.core.destinations.NavRoute

sealed interface DashboardNavRoute : NavRoute {

    @Serializable
    data object DashboardRoot : DashboardNavRoute

    @Serializable
    data object AllTransactionsNavRoute : DashboardNavRoute

}