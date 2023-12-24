import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.navigation.NavDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.tracing.trace
import com.gradation.lift.common.common.DispatcherProvider
import com.gradation.lift.navigation.Router
import com.gradation.lift.navigation.TopLevelNavDestination
import com.gradation.lift.navigation.navigation.*

@Stable
class AppState(
    val navController: NavHostController,
    private var dispatcherProvider: DispatcherProvider,
) {

    val currentDestination: NavDestination?
        @Composable get() = navController
            .currentBackStackEntryAsState().value?.destination


    val currentTopLevelDestination
        @Composable get() = when (currentDestination?.route) {
            Router.HOME_HOME_ROUTER_NAME -> TopLevelNavDestination.Home
            Router.HISTORY_HISTORY_ROUTER_NAME -> TopLevelNavDestination.History
            Router.MY_INFO_MY_INFO_ROUTER_NAME -> TopLevelNavDestination.MyInfo
            else -> null
        }


    val topLevelDestinations: List<TopLevelNavDestination> =
        TopLevelNavDestination.values().asList()


    fun navigateToTopLevelDestination(topLevelDestination: TopLevelNavDestination) {
        trace("Navigation: ${topLevelDestination.name}") {
            when (topLevelDestination) {
                TopLevelNavDestination.Home -> navController.navigateHomeGraph()
                TopLevelNavDestination.History -> navController.navigateHistoryGraph()
                TopLevelNavDestination.MyInfo -> navController.navigateMyInfoGraph()
            }
        }
    }


}


@Composable
fun rememberAppState(
    navController: NavHostController,
    dispatcherProvider: DispatcherProvider,
): AppState {
    return remember(navController) {
        AppState(
            navController,
            dispatcherProvider
        )
    }
}
