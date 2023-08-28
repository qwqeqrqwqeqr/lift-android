package com.gradation.lift.feature.history.history

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.gradation.lift.navigation.Router


@RequiresApi(Build.VERSION_CODES.O)
fun historyHistoryScreen(navController: NavController, navGraphBuilder: NavGraphBuilder) {
    navGraphBuilder.composable(Router.HISTORY_HISTORY_ROUTER_NAME) {

        HistoryHistoryRoute()

    }

}
