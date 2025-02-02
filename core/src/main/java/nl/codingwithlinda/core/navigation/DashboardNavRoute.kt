package nl.codingwithlinda.core.navigation

import kotlinx.serialization.Serializable

sealed interface DashboardNavRoute : NavRoute{

    @Serializable
    data object DashboardRoot : DashboardNavRoute
}