import com.gradation.lift.navigation.Router.HOME_ROUTER_NAME


import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.gradation.lift.create_routine.routine_detail.createRoutineRoutineDetailScreen
import com.gradation.lift.feature.create_routine.routile_set.CreateRoutineRoutineSetRoute
import com.gradation.lift.feature.history.historyScreen
import com.gradation.lift.feature.home.homeScreen
import com.gradation.lift.feature.my_info.myInfoScreen
import com.gradation.lift.feature.routine.routineScreen
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.gradation.lift.feature.create_routine.routile_set.createRoutineRoutineSetScreen
import com.gradation.lift.navigation.Router.CREATE_ROUTINE_GRAPH_ROUTER_NAME
import com.gradation.lift.navigation.Router.CREATE_ROUTINE_ROUTINE_SET_ROUTER_NAME
import com.gradation.lift.navigation.route.create_routine.CreateRoutineGraph

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun LiftNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    startDestination: String = HOME_ROUTER_NAME,
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        homeScreen(this)
        routineScreen(this)
        historyScreen(this)
        myInfoScreen(navController, this)
        createRoutineGraph(
            navController = navController,
            navGraphBuilder = this
        )

    }
}





fun createRoutineGraph(
    navController: NavController,
    navGraphBuilder: NavGraphBuilder,
) {
    CreateRoutineGraph { route, graphStartDestination ->
        navGraphBuilder.navigation(
            route = route,
            startDestination = graphStartDestination,
        ) {
            createRoutineRoutineSetScreen(navController = navController, this)
            createRoutineRoutineDetailScreen(navController, this)

        }
    }.createRoutineGraph(
        route = CREATE_ROUTINE_GRAPH_ROUTER_NAME,
        startDestination = CREATE_ROUTINE_ROUTINE_SET_ROUTER_NAME,
    )
}
