package nl.codingwithlinda.spendless.navigation.dashboard

import kotlinx.serialization.Serializable

sealed interface DashboardNavRoute : nl.codingwithlinda.spendless.navigation.core.NavRoute {

    @Serializable
    data object DashboardRoot : DashboardNavRoute

    @Serializable
    data object AllTransactionsNavRoute : DashboardNavRoute

}