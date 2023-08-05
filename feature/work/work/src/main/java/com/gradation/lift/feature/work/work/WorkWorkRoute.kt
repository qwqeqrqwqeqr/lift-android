package com.gradation.lift.feature.work.work

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.gradation.lift.designsystem.theme.LiftMaterialTheme
import com.gradation.lift.feature.work.work.component.WorkListScreen
import com.gradation.lift.feature.work.work.component.WorkRestScreen
import com.gradation.lift.feature.work.work.component.WorkWorkScreen
import com.gradation.lift.feature.work.work.data.WorkScreenState
import com.gradation.lift.feature.work.work.data.WorkSharedViewModel
import com.gradation.lift.feature.work.work.data.WorkWorkViewModel
import com.gradation.lift.model.utils.ModelDataGenerator.RoutineSetRoutine.routineSetRoutineModelList
import com.gradation.lift.navigation.Router

@SuppressLint("UnrememberedGetBackStackEntry")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WorkWorkRoute(
    navController: NavController,
    navigateWorkWorkToComplete: () -> Unit,
    navigateWorkToMain: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: WorkWorkViewModel = hiltViewModel(),

    ) {

    val workBackStackEntry =
        remember { navController.getBackStackEntry(Router.WORK_GRAPH_NAME) }
    val sharedViewModel: WorkSharedViewModel = hiltViewModel(workBackStackEntry)
    val routineSetRoutine by sharedViewModel.selectedRoutineSetList.collectAsStateWithLifecycle()

    val workScreenState : WorkScreenState by viewModel.workScreenState.collectAsStateWithLifecycle()

    when(val state = workScreenState){
        is WorkScreenState.ListScreen -> {
            WorkListScreen(
                modifier = modifier,
                onCloseClickTopBar = {},
                previousScreen = state.previousState
            )
        }
        WorkScreenState.RestScreen -> {
            WorkRestScreen(
                modifier = modifier,
                onCloseClickTopBar = {},
                routineSetRoutine = routineSetRoutine
            )
        }
        WorkScreenState.WorkScreen -> {
            WorkWorkScreen(
                modifier = modifier,
                onCloseClickTopBar = {},
                routineSetRoutine = routineSetRoutine
            )

        }
    }



}


