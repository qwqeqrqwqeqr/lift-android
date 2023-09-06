import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.gradation.lift.navigation.graph.*
import com.gradation.lift.oauth.common.OAuthConnectState
import kotlinx.coroutines.flow.MutableStateFlow

@SuppressLint("UnrememberedGetBackStackEntry")
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun LiftNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    startDestination: String,
    naverOAuthConnectState: MutableStateFlow<OAuthConnectState>,
    kakaoOAuthConnectState: MutableStateFlow<OAuthConnectState>,
    connectOAuthFromNaver: ()->Unit,
    connectOAuthFromKakao: ()->Unit,
) {


    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        homeGraphBuilder(
            navController = navController,
            navGraphBuilder = this,
        )
        myInfoGraphBuilder(navController,this)
        historyGraphBuilder(navController,this)
        createRoutineGraphBuilder(
            navController = navController,
            navGraphBuilder = this,
        )
        loginGraphBuilder(
            navController = navController,
            navGraphBuilder = this,
            naverOAuthConnectState =naverOAuthConnectState,
            kakaoOAuthConnectState =kakaoOAuthConnectState,
            connectOAuthFromNaver = { connectOAuthFromNaver() },
            connectOAuthFromKakao = { connectOAuthFromKakao() }
        )
        registerDetailGraphBuilder(
            navController = navController,
            navGraphBuilder = this
        )
        updateRoutineGraphBuilder(
            navController = navController,
            navGraphBuilder = this
        )
        workGraphBuilder(
            navController = navController,
            navGraphBuilder = this
        )
    }
}






