import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.gradation.lift.navigation.graph.*
import com.gradation.lift.oauth.state.OAuthConnectState

@SuppressLint("UnrememberedGetBackStackEntry")
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun LiftNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    startDestination: String,
    naverOAuthConnectState: OAuthConnectState,
    kakaoOauthConnectState: OAuthConnectState,
    signInNaver: ()->Unit,
    signInKakao: ()->Unit,
) {


    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        mainGraphBuilder(
            navController = navController,
            navGraphBuilder = this,
        )
        createRoutineGraphBuilder(
            navController = navController,
            navGraphBuilder = this,
        )
        loginGraphBuilder(
            navController = navController,
            navGraphBuilder = this,
            naverOAuthConnectState =naverOAuthConnectState,
            kakaoOauthConnectState =kakaoOauthConnectState,
            signInNaver = { signInNaver() },
            signInKakao = { signInKakao() }
        )
        registerDetailGraphBuilder(
            navController = navController,
            navGraphBuilder = this
        )
        workGraphBuilder(
            navController = navController,
            navGraphBuilder = this
        )
    }
}






