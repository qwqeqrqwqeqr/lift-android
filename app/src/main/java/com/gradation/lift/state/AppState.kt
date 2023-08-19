import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.navigation.NavDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.tracing.trace
import com.gradation.lift.common.common.DispatcherProvider
import com.gradation.lift.domain.usecase.auth.ConnectOAuthFromKakaoUseCase
import com.gradation.lift.domain.usecase.auth.ConnectOAuthFromNaverUseCase
import com.gradation.lift.navigation.Router
import com.gradation.lift.navigation.graph.TopLevelNavDestination
import com.gradation.lift.navigation.navigation.*
import com.gradation.lift.oauth.state.OAuthConnectState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

@Stable
class AppState(
    val navController: NavHostController,
    var connectOAuthFromNaverUseCase: ConnectOAuthFromNaverUseCase,
    var connectOAuthFromKakaoUseCase: ConnectOAuthFromKakaoUseCase,
    private var dispatcherProvider: DispatcherProvider,
) {


    val naverOAuthConnectState: MutableStateFlow<OAuthConnectState> =
        MutableStateFlow(OAuthConnectState.None)
    val kakaoOAuthConnectState: MutableStateFlow<OAuthConnectState> =
        MutableStateFlow(OAuthConnectState.None)


    val currentDestination: NavDestination?
        @Composable get() = navController
            .currentBackStackEntryAsState().value?.destination


    val currentTopLevelDestination
        @Composable get() = when (currentDestination?.route) {
            Router.HOME_HOME_ROUTER_NAME -> TopLevelNavDestination.Home
            Router.HISTORY_ANALYTICS_ROUTER_NAME -> TopLevelNavDestination.History
            Router.HISTORY_DAILY_LOG_ROUTER_NAME -> TopLevelNavDestination.History
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

    fun connectOAuthFromNaver() {
        CoroutineScope(dispatcherProvider.default).launch {
            connectOAuthFromNaverUseCase().collect { naverOAuthConnectState.value = it }
        }
    }

    fun connectOAuthFromKakao() {
        CoroutineScope(dispatcherProvider.default).launch {
            connectOAuthFromKakaoUseCase().collect { kakaoOAuthConnectState.value = it }
        }
    }
}


@Composable
fun rememberAppState(
    navController: NavHostController,
    connectOAuthFromNaverUseCase: ConnectOAuthFromNaverUseCase,
    connectOAuthFromKakaoUseCase: ConnectOAuthFromKakaoUseCase,
    dispatcherProvider: DispatcherProvider,
): AppState {
    return remember(navController) {
        AppState(
            navController,
            connectOAuthFromNaverUseCase,
            connectOAuthFromKakaoUseCase,
            dispatcherProvider
        )
    }
}
