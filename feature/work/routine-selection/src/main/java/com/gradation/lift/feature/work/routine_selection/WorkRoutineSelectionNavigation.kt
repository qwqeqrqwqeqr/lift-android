package com.gradation.lift.feature.work.routine_selection

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.gradation.lift.navigation.Router.WORK_ROUTINE_SELECTION_ROUTER_NAME
import com.gradation.lift.navigation.navigation.navigateSelectionRoutineToWorkInWorkGraph
import com.gradation.lift.navigation.navigation.navigateWorkGraphToHomeGraph
import com.gradation.lift.navigation.saved_state.SavedStateHandleKey
import com.gradation.lift.navigation.saved_state.getValueSavedStateHandle

/**
 * [selectedRoutineSetId] 이전화면 (홈 화면) 에서 선택된 루틴의 아이디
 * 루틴세트 카드를 눌러 접근했을 경우에만 번호를 보유하고 있음
 * @since 2023-08-22 12:49:33
 * */
@RequiresApi(Build.VERSION_CODES.O)
fun workRoutineSelectionScreen(
    navController: NavController,
    navGraphBuilder: NavGraphBuilder,
) {
    navGraphBuilder.composable(WORK_ROUTINE_SELECTION_ROUTER_NAME) {

        val selectedRoutineSetId: Int? =
            navController.getValueSavedStateHandle<Int>(SavedStateHandleKey.WorkKey.ROUTINE_SET_ID_KEY)
        val navigateSelectionRoutineToWorkInWorkGraph: () -> Unit =
            { navController.navigateSelectionRoutineToWorkInWorkGraph() }
        val navigateWorkGraphToHomeGraph: () -> Unit =
            { navController.navigateWorkGraphToHomeGraph() }

        WorkRoutineSelectionRoute(
            navController = navController,
            navigateSelectionRoutineToWorkInWorkGraph = navigateSelectionRoutineToWorkInWorkGraph,
            navigateWorkGraphToHomeGraph = navigateWorkGraphToHomeGraph,
            selectedRoutineSetId = selectedRoutineSetId
        )
    }

}
