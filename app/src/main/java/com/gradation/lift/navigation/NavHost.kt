import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.gradation.lift.navigation.graph.*

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun LiftNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    startDestination: String,
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        mainGraph(
            navController = navController,
            navGraphBuilder = this
        )
        createRoutineGraph(
            navController = navController,
            navGraphBuilder = this
        )
        loginGraph(
            navController = navController,
            navGraphBuilder = this
        )
        registerDetailGraph(
            navController = navController,
            navGraphBuilder = this
        )
        readyWorkGraph(
            navController = navController,
            navGraphBuilder = this
        )
        workGraph(
            navController = navController,
            navGraphBuilder = this
        )
    }
}






