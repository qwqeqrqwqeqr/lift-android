import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.navigation.NavDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.tracing.trace
import com.gradation.lift.common.common.DispatcherProvider
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

    val isShowBottomBar: Boolean
        @Composable get() = currentDestination?.let { destination ->
            (destination.route in topLevelDestinations.map { it.route })
        } ?: false

    val topLevelDestinations: List<TopLevelNavDestination> =
        TopLevelNavDestination.values().asList()


    fun navigateToTopLevelDestination(): (TopLevelNavDestination) -> Unit = { topLevelDestination ->
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
            dispatcherProvider,
        )
    }
}
