package com.gradation.lift.create_routine.routine

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
import com.gradation.lift.create_routine.routine.component.NavigationView
import com.gradation.lift.create_routine.routine.component.ProfilePictureView
import com.gradation.lift.create_routine.routine.component.RoutineListView
import com.gradation.lift.create_routine.routine.data.model.IndexWorkSet
import com.gradation.lift.designsystem.component.LiftBackTopBar
import com.gradation.lift.designsystem.theme.LiftMaterialTheme
import com.gradation.lift.designsystem.theme.LiftTheme
import com.gradation.lift.feature.create_routine.routine_set.data.viewmodel.CreateRoutineSharedViewModel
import com.gradation.lift.model.model.work.WorkSet
import com.gradation.lift.model.utils.ModelDataGenerator.RoutineSetRoutine.createRoutineModel
import com.gradation.lift.navigation.Router

@SuppressLint("UnrememberedGetBackStackEntry")
@Composable
fun CreateRoutineRoutineRoute(
    modifier: Modifier = Modifier,
    navController: NavController,
    navigateRoutineToFindWorkCategoryInCreateRoutineGraph: () -> Unit,
    navigateRoutineToRoutineSetInCreateRoutineGraph: () -> Unit,
    viewModel: CreateRoutineRoutineViewModel = hiltViewModel(),
) {
    val crateRoutineBackStackEntry: NavBackStackEntry =
        remember { navController.getBackStackEntry(Router.CREATE_ROUTINE_GRAPH_NAME) }
    val sharedViewModel: CreateRoutineSharedViewModel = hiltViewModel(crateRoutineBackStackEntry)

    val workSetList: List<IndexWorkSet> by viewModel.indexWorkSetList.collectAsStateWithLifecycle()
    val createRoutineCondition: Boolean by viewModel.createRoutineCondition.collectAsStateWithLifecycle()
    val tempWorkCategory: String by sharedViewModel.tempWorkCategory.collectAsStateWithLifecycle()


    val addWorkSet: () -> Unit = viewModel.addWorkSet()
    val updateWorkSet: (IndexWorkSet) -> Unit = viewModel.updateWorkSet()
    val removeWorkSet: (IndexWorkSet) -> Unit = viewModel.removeWorkSet()


    val addRoutine: (List<WorkSet>) -> Unit = sharedViewModel.addRoutine()


    val focusManager = LocalFocusManager.current
    CreateRoutineRoutineScreen(
        modifier,
        workSetList,
        createRoutineCondition,
        tempWorkCategory,
        addWorkSet,
        updateWorkSet,
        removeWorkSet,
        addRoutine,
        navigateRoutineToFindWorkCategoryInCreateRoutineGraph,
        navigateRoutineToRoutineSetInCreateRoutineGraph,
        focusManager
    )

    BackHandler(onBack = navigateRoutineToFindWorkCategoryInCreateRoutineGraph)
}

@Composable
fun CreateRoutineRoutineScreen(
    modifier: Modifier = Modifier,
    workSetList: List<IndexWorkSet>,
    createRoutineCondition: Boolean,
    tempWorkCategory: String,
    addWorkSet: () -> Unit,
    updateWorkSet: (IndexWorkSet) -> Unit,
    removeWorkSet: (IndexWorkSet) -> Unit,
    addRoutine: (List<WorkSet>) -> Unit,
    navigateRoutineToFindWorkCategoryInCreateRoutineGraph: () -> Unit,
    navigateRoutineToRoutineSetInCreateRoutineGraph: () -> Unit,
    focusManager: FocusManager,
) {
    Scaffold(
        topBar = {
            LiftBackTopBar(
                title = tempWorkCategory,
                onBackClickTopBar = navigateRoutineToFindWorkCategoryInCreateRoutineGraph,
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
                NavigationView(
                    modifier,
                    createRoutineCondition,
                    workSetList,
                    addRoutine,
                    navigateRoutineToRoutineSetInCreateRoutineGraph
                )
            }
        }
    }
}

@Preview
@Composable
fun CreateRoutineRoutineScreenPreview() {
    LiftMaterialTheme {
        CreateRoutineRoutineScreen(
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
            tempWorkCategory = createRoutineModel.workCategory,
            addWorkSet = {},
            updateWorkSet = {},
            removeWorkSet = {},
            addRoutine = {},
            navigateRoutineToFindWorkCategoryInCreateRoutineGraph = {},
            navigateRoutineToRoutineSetInCreateRoutineGraph = {},
            focusManager = LocalFocusManager.current
        )
    }
}
