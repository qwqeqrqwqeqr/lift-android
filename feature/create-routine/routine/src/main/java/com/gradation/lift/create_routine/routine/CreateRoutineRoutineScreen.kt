package com.gradation.lift.create_routine.routine

import android.annotation.SuppressLint
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import com.gradation.lift.create_routine.routine.component.CreateRoutineView
import com.gradation.lift.create_routine.routine.component.RoutineListView
import com.gradation.lift.create_routine.routine.data.state.KeypadState
import com.gradation.lift.create_routine.routine.data.model.IndexWorkSet
import com.gradation.lift.designsystem.component.LiftBackTopBar
import com.gradation.lift.designsystem.keypad.RepetitionKeypadContainer
import com.gradation.lift.designsystem.keypad.WeightKeypadContainer
import com.gradation.lift.designsystem.theme.LiftTheme
import com.gradation.lift.feature.create_routine.routine_set.data.viewmodel.CreateRoutineSharedViewModel
import com.gradation.lift.model.model.work.WorkSet
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

    val tempWorkCategory: String by sharedViewModel.tempWorkCategory.collectAsStateWithLifecycle()
    val indexWorkSetList: List<IndexWorkSet> by viewModel.indexWorkSetList.collectAsStateWithLifecycle()
    val keypadState: KeypadState by viewModel.keypadState.collectAsStateWithLifecycle()
    val createRoutineCondition: Boolean by viewModel.createRoutineCondition.collectAsStateWithLifecycle()


    val updateWeight: (Int, String) -> Unit = viewModel.updateWeight()
    val updateRepetition: (Int, String) -> Unit = viewModel.updateRepetition()
    val appendWorkSet: () -> Unit = viewModel.appendWorkSet()
    val removeWorkSet: (IndexWorkSet) -> Unit = viewModel.removeWorkSet()
    val updateKeypadState: (KeypadState) -> Unit = viewModel.updateKeypadState()

    val addRoutine: (List<WorkSet>) -> Unit = sharedViewModel.addRoutine()


    CreateRoutineRoutineScreen(
        modifier,
        tempWorkCategory,
        indexWorkSetList,
        keypadState,
        createRoutineCondition,
        updateWeight,
        updateRepetition,
        appendWorkSet,
        removeWorkSet,
        updateKeypadState,
        addRoutine,
        navigateRoutineToFindWorkCategoryInCreateRoutineGraph,
        navigateRoutineToRoutineSetInCreateRoutineGraph
    )

    BackHandler(onBack = navigateRoutineToFindWorkCategoryInCreateRoutineGraph)
}

@Composable
fun CreateRoutineRoutineScreen(
    modifier: Modifier = Modifier,
    tempWorkCategory: String,
    indexWorkSetList: List<IndexWorkSet>,
    keypadState: KeypadState,
    createRoutineCondition: Boolean,
    updateWeight: (Int, String) -> Unit,
    updateRepetition: (Int, String) -> Unit,
    appendWorkSet: () -> Unit,
    removeWorkSet: (IndexWorkSet) -> Unit,
    updateKeypadState: (KeypadState) -> Unit,
    addRoutine: (List<WorkSet>) -> Unit,
    navigateRoutineToFindWorkCategoryInCreateRoutineGraph: () -> Unit,
    navigateRoutineToRoutineSetInCreateRoutineGraph: () -> Unit,
) {
    when (keypadState) {
        KeypadState.None -> {}
        is KeypadState.Repetition -> {
            RepetitionKeypadContainer(
                modifier = modifier.fillMaxWidth(),
                clearNumber = {
                    keypadState.clearNumber()
                    updateRepetition(keypadState.index, keypadState.repetition.value)
                },
                appendNumber = {
                    keypadState.appendNumber(it)
                    updateRepetition(keypadState.index, keypadState.repetition.value)

                },
                plusNumber = {
                    keypadState.plusNumber(it)
                    updateRepetition(keypadState.index, keypadState.repetition.value)
                },
                minusNumber = {
                    keypadState.minusNumber(it)
                    updateRepetition(keypadState.index, keypadState.repetition.value)
                },
                onDismissRequest = {
                    keypadState.onDone()
                    updateRepetition(keypadState.index, keypadState.repetition.value)
                    updateKeypadState(KeypadState.None)
                }
            )

        }

        is KeypadState.Weight -> {
            WeightKeypadContainer(
                clearNumber = {
                    keypadState.clearNumber()
                    updateWeight(keypadState.index, keypadState.weight.value)
                },
                appendNumber = {
                    keypadState.appendNumber(it)
                    updateWeight(keypadState.index, keypadState.weight.value)
                },
                appendPoint = {
                    keypadState.appendPoint(it)
                    updateWeight(keypadState.index, keypadState.weight.value)
                },
                plusNumber = {
                    keypadState.plusNumber(it)
                    updateWeight(keypadState.index, keypadState.weight.value)
                },
                minusNumber = {
                    keypadState.minusNumber(it)
                    updateWeight(keypadState.index, keypadState.weight.value)
                },
                onDismissRequest = {
                    keypadState.onDone()
                    updateWeight(keypadState.index, keypadState.weight.value)
                    updateKeypadState(KeypadState.None)
                })
        }
    }

    Scaffold(
        topBar = {
            LiftBackTopBar(
                title = tempWorkCategory,
                onBackClickTopBar = navigateRoutineToFindWorkCategoryInCreateRoutineGraph,
            )
        },
        modifier = modifier.fillMaxSize(),
    ) {
        Surface(
            color = LiftTheme.colorScheme.no5,
            modifier = modifier
                .padding(it)
        ) {
            Column(
                modifier = modifier
                    .fillMaxHeight()
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(32.dp)
            ) {
                Column(modifier = modifier.weight(1f)) {
                    RoutineListView(
                        modifier,
                        indexWorkSetList,
                        keypadState,
                        updateKeypadState,
                        appendWorkSet,
                        removeWorkSet
                    )
                }
                CreateRoutineView(
                    modifier,
                    indexWorkSetList,
                    addRoutine,
                    createRoutineCondition,
                    navigateRoutineToRoutineSetInCreateRoutineGraph
                )
            }
        }
    }
}
