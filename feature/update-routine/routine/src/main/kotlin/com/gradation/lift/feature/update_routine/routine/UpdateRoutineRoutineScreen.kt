package com.gradation.lift.feature.update_routine.routine

import android.annotation.SuppressLint
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import com.gradation.lift.designsystem.component.LiftBackTopBar
import com.gradation.lift.designsystem.theme.LiftMaterialTheme
import com.gradation.lift.designsystem.theme.LiftTheme
import com.gradation.lift.feature.update_routine.routine.component.NavigationView
import com.gradation.lift.feature.update_routine.routine.component.ProfilePictureView
import com.gradation.lift.feature.update_routine.routine.component.RoutineListView
import com.gradation.lift.feature.update_routine.routine.data.model.IndexWorkSet
import com.gradation.lift.model.model.work.WorkSet
import com.gradation.lift.model.utils.ModelDataGenerator.RoutineSetRoutine.createRoutineModel
import com.gradation.lift.navigation.Router


@SuppressLint("UnrememberedGetBackStackEntry")
@Composable
fun UpdateRoutineRoutineRoute(
    modifier: Modifier = Modifier,
    navController: NavController,
    navigateRoutineToFindWorkCategoryInUpdateRoutineGraph: () -> Unit,
    navigateRoutineToRoutineSetInUpdateRoutineGraph: () -> Unit,
    viewModel: UpdateRoutineRoutineViewModel = hiltViewModel(),
) {
    val updateRoutineBackStackEntry: NavBackStackEntry =
        remember { navController.getBackStackEntry(Router.UPDATE_ROUTINE_GRAPH_NAME) }

    val workSetList: List<IndexWorkSet> by viewModel.indexWorkSetList.collectAsStateWithLifecycle()
    val createRoutineCondition: Boolean by viewModel.createRoutineCondition.collectAsStateWithLifecycle()


    val addWorkSet: () -> Unit = viewModel.addWorkSet()
    val updateWorkSet: (IndexWorkSet) -> Unit = viewModel.updateWorkSet()
    val removeWorkSet: (IndexWorkSet) -> Unit = viewModel.removeWorkSet()




    val focusManager = LocalFocusManager.current
    UpdateRoutineRoutineScreen(
        modifier,
        workSetList,
        createRoutineCondition,
        addWorkSet,
        updateWorkSet,
        removeWorkSet,
        navigateRoutineToFindWorkCategoryInUpdateRoutineGraph,
        navigateRoutineToRoutineSetInUpdateRoutineGraph,
        focusManager
    )

    BackHandler(onBack = navigateRoutineToFindWorkCategoryInUpdateRoutineGraph)
}

@Composable
fun UpdateRoutineRoutineScreen(
    modifier: Modifier = Modifier,
    workSetList: List<IndexWorkSet>,
    createRoutineCondition: Boolean,
    addWorkSet: () -> Unit,
    updateWorkSet: (IndexWorkSet) -> Unit,
    removeWorkSet: (IndexWorkSet) -> Unit,
    navigateRoutineToFindWorkCategoryInUpdateRoutineGraph: () -> Unit,
    navigateRoutineToRoutineSetInUpdateRoutineGraph: () -> Unit,
    focusManager: FocusManager,
) {
    Scaffold(
        topBar = {
            LiftBackTopBar(
                title = "",
                onBackClickTopBar = navigateRoutineToFindWorkCategoryInUpdateRoutineGraph,
            )
        },
        modifier = modifier.fillMaxSize(),
    ) { PaddingValues ->
        Surface(
            color = LiftTheme.colorScheme.no5,
            modifier = modifier.fillMaxSize()
        ) {
            Column(
                modifier = modifier
                    .padding(PaddingValues)
                    .padding(16.dp)
                    .fillMaxWidth()
            ) {
                ProfilePictureView(modifier)
                Spacer(modifier = modifier.padding(18.dp))
                RoutineListView(
                    modifier,
                    workSetList,
                    addWorkSet,
                    updateWorkSet,
                    removeWorkSet,
                    focusManager
                )
                Spacer(modifier = modifier.padding(4.dp))
            }
        }
    }
}

@Preview
@Composable
fun UpdateRoutineRoutineScreenPreview() {
    LiftMaterialTheme {
        UpdateRoutineRoutineScreen(
            workSetList = createRoutineModel
                .workSetList
                .mapIndexed { index, workSet ->
                    IndexWorkSet(
                        index + 1,
                        workSet.weight.toString(),
                        workSet.repetition.toString()
                    )
                },
            createRoutineCondition = true,
            addWorkSet = {},
            updateWorkSet = {},
            removeWorkSet = {},
            navigateRoutineToFindWorkCategoryInUpdateRoutineGraph = {},
            navigateRoutineToRoutineSetInUpdateRoutineGraph = {},
            focusManager = LocalFocusManager.current
        )
    }
}
