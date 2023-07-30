import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.gradation.lift.navigation.Router
import com.gradation.lift.navigation.graph.*

@SuppressLint("UnrememberedGetBackStackEntry")
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun LiftNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    startDestination: String,
) {
    val crateRoutineBackStackEntry = remember { navController.getBackStackEntry(Router.CREATE_ROUTINE_GRAPH_ROUTER_NAME) }
    val createRoutineViewModel: CreateRoutineViewModel = viewModel(crateRoutineBackStackEntry)

    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        mainGraphBuilder(
            navController = navController,
            navGraphBuilder = this
        )
        createRoutineGraphBuilder(
            navController = navController,
            navGraphBuilder = this,
            sharedViewModel = createRoutineViewModel
        )
        loginGraphBuilder(
            navController = navController,
            navGraphBuilder = this
        )
        registerDetailGraphBuilder(
            navController = navController,
            navGraphBuilder = this
        )
        readyWorkGraphBuilder(
            navController = navController,
            navGraphBuilder = this
        )
        workGraphBuilder(
            navController = navController,
            navGraphBuilder = this
        )
    }
}






