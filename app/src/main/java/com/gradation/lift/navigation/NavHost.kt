

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.google.accompanist.systemuicontroller.SystemUiController
import com.gradation.lift.MainActivityViewModel
import com.gradation.lift.feature.history.historyScreen
import com.gradation.lift.feature.home.homeScreen
import com.gradation.lift.feature.my_info.myInfoScreen
import com.gradation.lift.feature.routine.routineScreen
import com.gradation.lift.navigation.graph.createRoutineGraph
import com.gradation.lift.navigation.graph.loginGraph

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
        homeScreen(navController,this)
        routineScreen(navController,this)
        historyScreen(navController,this)
        myInfoScreen(navController, this)
        createRoutineGraph(
            navController = navController,
            navGraphBuilder = this
        )
        loginGraph(
            navController = navController,
            navGraphBuilder = this
        )
    }
}






