package com.gradation.lift.create_routine.routine

import android.annotation.SuppressLint
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.gradation.lift.create_routine.routine.component.RoutineListView
import com.gradation.lift.designsystem.component.LiftBackTopBar
import com.gradation.lift.designsystem.component.LiftButton
import com.gradation.lift.designsystem.theme.LiftMaterialTheme
import com.gradation.lift.designsystem.theme.LiftTheme
import com.gradation.lift.feature.create_routine.routine_set.data.CreateRoutineSharedViewModel
import com.gradation.lift.model.model.routine.CreateRoutine
import com.gradation.lift.model.utils.ModelDataGenerator.RoutineSetRoutine.createRoutineModel
import com.gradation.lift.model.model.work.WorkSet
import com.gradation.lift.navigation.Router

@SuppressLint("UnrememberedGetBackStackEntry")
@Composable
fun CreateRoutineRoutineRoute(
    modifier: Modifier = Modifier,
    navController: NavController,
    navigateRoutineToFindWorkCategory: () -> Unit,
    navigateRoutineToRoutineSet: () -> Unit,
    viewModel: CreateRoutineRoutineViewModel = hiltViewModel(),
) {
    val crateRoutineBackStackEntry =
        remember { navController.getBackStackEntry(Router.CREATE_ROUTINE_GRAPH_NAME) }
    val sharedViewModel: CreateRoutineSharedViewModel = hiltViewModel(crateRoutineBackStackEntry)

    val tempWorkCategory = sharedViewModel.tempWorkCategory.collectAsStateWithLifecycle()
    val onCreateRoutine = sharedViewModel.addRoutineSet()
    val workSetList = viewModel.workSetList.collectAsStateWithLifecycle()
    val createRoutineCondition = viewModel.createRoutineCondition.collectAsStateWithLifecycle()

    val updateWorkSet = viewModel.updateWorkSet
    val addWorkSet = viewModel.addWorkSet
    val removeWorkSet = viewModel.removeWorkSet


    val focusManager = LocalFocusManager.current
    CreateRoutineRoutineScreen(
        modifier = modifier,
        onBackClickTopBar = navigateRoutineToFindWorkCategory,
        onCreateRoutine = {
            onCreateRoutine(it)
            navigateRoutineToRoutineSet()
        },
        tempWorkCategory = tempWorkCategory,
        workSetList = workSetList,
        createRoutineCondition = createRoutineCondition,
        updateWorkSet = updateWorkSet,
        addWorkSet = addWorkSet,
        removeWorkSet = removeWorkSet,
        focusManager = focusManager
    )

    BackHandler(onBack = navigateRoutineToFindWorkCategory)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateRoutineRoutineScreen(
    modifier: Modifier = Modifier,
    onBackClickTopBar: () -> Unit,
    onCreateRoutine: (CreateRoutine) -> Unit,
    tempWorkCategory: State<String>,
    workSetList: State<List<IndexWorkSet>>,
    createRoutineCondition: State<Boolean>,
    updateWorkSet: (IndexWorkSet) -> Unit,
    addWorkSet: () -> Unit,
    removeWorkSet: (IndexWorkSet) -> Unit,
    focusManager: FocusManager
) {
    Scaffold(
        topBar = {
            LiftBackTopBar(
                title = tempWorkCategory.value,
                onBackClickTopBar = onBackClickTopBar,
            )
        }, modifier = modifier.fillMaxSize(), floatingActionButton =
        {
            LiftButton(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(
                        start = 16.dp,
                        end = 16.dp,
                    ),
                onClick = {
                    onCreateRoutine(
                        CreateRoutine(
                            tempWorkCategory.value,
                            workSetList.value.map {
                                WorkSet(it.weight.toFloat(), it.repetition.toInt())
                            }
                        )
                    )
                },
                enabled = createRoutineCondition.value
            ) {
                Text(
                    text = "등록하기",
                    style = LiftTheme.typography.no3,
                    color = LiftTheme.colorScheme.no5,
                )
            }
        },
        floatingActionButtonPosition = FabPosition.Center
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
                Box(
                    modifier = modifier
                        .background(
                            color = LiftTheme.colorScheme.no1,
                            shape = RoundedCornerShape(size = 12.dp)
                        )
                        .align(alignment = Alignment.CenterHorizontally)
                        .size(128.dp)
                )
                Spacer(modifier = modifier.padding(18.dp))
                Text(
                    text = "루틴 만들기",
                    style = LiftTheme.typography.no3,
                    color = LiftTheme.colorScheme.no3,
                )
                Spacer(modifier = modifier.padding(4.dp))
                RoutineListView(
                    modifier = modifier,
                    workSetList = workSetList,
                    updateWorkSet = updateWorkSet,
                    addWorkSet = addWorkSet,
                    removeWorkSet = removeWorkSet,
                    focusManager = focusManager
                )

            }
        }
    }
}

@SuppressLint("UnrememberedMutableState")
@Preview
@Composable
fun CreateRoutineRoutineScreenPreview() {
    LiftMaterialTheme {
        CreateRoutineRoutineScreen(
            modifier = Modifier,
            onBackClickTopBar = {},
            tempWorkCategory = mutableStateOf(
                "숄더프레스"
            ),
            onCreateRoutine = {},
            workSetList = mutableStateOf(
                createRoutineModel
                    .workSetList
                    .mapIndexed { index, workSet ->
                        IndexWorkSet(
                            index + 1,
                            workSet.weight.toString(),
                            workSet.repetition.toString()
                        )

                    }),
            createRoutineCondition = mutableStateOf(false),
            updateWorkSet = {},
            addWorkSet = {},
            removeWorkSet = {},
            focusManager = LocalFocusManager.current
        )
    }
}