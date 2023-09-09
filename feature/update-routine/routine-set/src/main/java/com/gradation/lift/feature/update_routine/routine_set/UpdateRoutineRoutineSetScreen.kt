package com.gradation.lift.feature.update_routine.routine_set

import android.annotation.SuppressLint
import android.os.Build
import androidx.activity.compose.BackHandler
import androidx.annotation.RequiresApi
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import com.gradation.lift.common.utils.Validator
import com.gradation.lift.designsystem.component.LiftBackTopBar
import com.gradation.lift.designsystem.component.LiftErrorSnackBar
import com.gradation.lift.designsystem.extensions.noRippleClickable
import com.gradation.lift.designsystem.theme.LiftTheme
import com.gradation.lift.feature.update_routine.routine_set.data.state.UpdateRoutineState
import com.gradation.lift.feature.update_routine.routine_selection.data.UpdateRoutineSharedViewModel
import com.gradation.lift.feature.update_routine.routine_selection.data.model.WeekDateSelection
import com.gradation.lift.feature.update_routine.routine_set.component.DeleteDialog
import com.gradation.lift.feature.update_routine.routine_set.component.RoutineSetDescriptionView
import com.gradation.lift.feature.update_routine.routine_set.component.RoutineSetNameView
import com.gradation.lift.feature.update_routine.routine_set.component.RoutineSetPictureView
import com.gradation.lift.feature.update_routine.routine_set.component.RoutineSetRoutineView
import com.gradation.lift.feature.update_routine.routine_set.component.WeekdayCardListView
import com.gradation.lift.feature.update_routine.routine_set.data.UpdateRoutineRoutineSetViewModel
import com.gradation.lift.model.model.date.Weekday
import com.gradation.lift.model.model.routine.UpdateRoutine
import com.gradation.lift.model.model.routine.UpdateRoutineSetRoutine
import com.gradation.lift.navigation.Router

@RequiresApi(Build.VERSION_CODES.O)
@SuppressLint("UnrememberedGetBackStackEntry")
@Composable
fun UpdateRoutineRoutineSetRoute(
    navController: NavController,
    navigateRoutineSetToRoutineSelectionInUpdateRoutineGraph: () -> Unit,
    navigateRoutineSetToFindWorkCategoryInUpdateRoutineGraph: () -> Unit,
    navigateRoutineSetToProfilePictureInUpdateRoutineGraph: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: UpdateRoutineRoutineSetViewModel = hiltViewModel(),
) {
    val workBackStackEntry: NavBackStackEntry =
        remember { navController.getBackStackEntry(Router.UPDATE_ROUTINE_GRAPH_NAME) }
    val sharedViewModel: UpdateRoutineSharedViewModel = hiltViewModel(workBackStackEntry)

    val updateRoutineState: UpdateRoutineState by viewModel.updateRoutineState.collectAsStateWithLifecycle()
    val selectedRoutineSetRoutine: UpdateRoutineSetRoutine by sharedViewModel.selectedRoutineSetRoutine.collectAsStateWithLifecycle()

    val routineSetNameValidator: Validator by sharedViewModel.routineSetNameValidator.collectAsStateWithLifecycle()
    val routineSetDescriptionValidator: Validator by sharedViewModel.routineSetDescriptionValidator.collectAsStateWithLifecycle()
    val weekDateSelectionList: List<WeekDateSelection> by sharedViewModel.weekDateSelectionList.collectAsStateWithLifecycle()


    val onVisibleDeleteDialog: Boolean by viewModel.onVisibleDeleteDialog.collectAsStateWithLifecycle()


    val deleteRoutineSetRoutine: (Int) -> Unit = viewModel.deleteRoutineSetRoutine()
    val updateRoutineSetRoutine: (UpdateRoutineSetRoutine) -> Unit =
        viewModel.updateRoutineSetRoutine()

    val updateRoutineSetName: (String) -> Unit = sharedViewModel.updateRoutineSetName()
    val updateRoutineSetDescription: (String) -> Unit =
        sharedViewModel.updateRoutineSetDescription()
    val updateRoutineSetPicture: (String) -> Unit = sharedViewModel.updateRoutineSetPicture()
    val updateRoutineSetWeekday: (Weekday) -> Unit = sharedViewModel.updateRoutineSetWeekday()


    val updateUpdateRoutineState: (UpdateRoutineState) -> Unit =
        viewModel.updateUpdateRoutineState()
    val updateSelectedRoutineSetRoutine: (UpdateRoutineSetRoutine) -> Unit =
        sharedViewModel.updateSelectedRoutineSetRoutine()
    val removeRoutine: (UpdateRoutine) -> Unit = sharedViewModel.removeRoutine()

    val visibleDeleteDialog: () -> Unit = viewModel.visibleDeleteDialog()
    val invisibleDeleteDialog: () -> Unit = viewModel.invisibleDeleteDialog()

    val snackbarHostState: SnackbarHostState by remember { mutableStateOf(SnackbarHostState()) }
    val focusManager: FocusManager = LocalFocusManager.current
    val scrollState: ScrollState = rememberScrollState()


    when (val updateRoutineStateResult = updateRoutineState) {
        is UpdateRoutineState.Fail -> {
            LaunchedEffect(true) {
                snackbarHostState.showSnackbar(
                    message = updateRoutineStateResult.message, duration = SnackbarDuration.Short
                )
                updateUpdateRoutineState(UpdateRoutineState.None)
            }
        }

        UpdateRoutineState.None -> {}
        is UpdateRoutineState.Success -> {
            LaunchedEffect(true) {
                navigateRoutineSetToRoutineSelectionInUpdateRoutineGraph()
            }
        }
    }

    BackHandler(enabled = true, onBack = navigateRoutineSetToRoutineSelectionInUpdateRoutineGraph)

    UpdateRoutineRoutineSetScreen(
        modifier,
        selectedRoutineSetRoutine,
        onVisibleDeleteDialog,
        routineSetNameValidator,
        routineSetDescriptionValidator,
        weekDateSelectionList,
        updateRoutineSetName,
        updateRoutineSetDescription,
        updateRoutineSetWeekday,
        deleteRoutineSetRoutine,
        updateRoutineSetRoutine,
        removeRoutine,
        visibleDeleteDialog,
        invisibleDeleteDialog,
        navigateRoutineSetToRoutineSelectionInUpdateRoutineGraph,
        navigateRoutineSetToFindWorkCategoryInUpdateRoutineGraph,
        navigateRoutineSetToProfilePictureInUpdateRoutineGraph,
        snackbarHostState,
        focusManager,
        scrollState
    )
}


@Composable
internal fun UpdateRoutineRoutineSetScreen(
    modifier: Modifier = Modifier,
    selectedRoutineSetRoutine: UpdateRoutineSetRoutine,
    onVisibleDeleteDialog: Boolean,
    routineSetNameValidator: Validator,
    routineSetDescriptionValidator: Validator,
    weekDateSelectionList: List<WeekDateSelection>,
    updateRoutineSetName: (String) -> Unit,
    updateRoutineSetDescription: (String) -> Unit,
    updateRoutineSetWeekday: (Weekday) -> Unit,
    deleteRoutineSetRoutine: (Int) -> Unit,
    updateRoutineSetRoutine: (UpdateRoutineSetRoutine) -> Unit,
    removeRoutine: (UpdateRoutine) -> Unit,
    visibleDeleteDialog: () -> Unit,
    invisibleDeleteDialog: () -> Unit,
    navigateRoutineSetToRoutineSelectionInUpdateRoutineGraph: () -> Unit,
    navigateRoutineSetToFindWorkCategoryInUpdateRoutineGraph: () -> Unit,
    navigateRoutineSetToProfilePictureInUpdateRoutineGraph: () -> Unit,
    snackbarHostState: SnackbarHostState,
    focusManager: FocusManager,
    scrollState: ScrollState
) {
    if (onVisibleDeleteDialog) {
        Surface(
            color = LiftTheme.colorScheme.no5.copy(alpha = 0.7f),
            modifier = modifier.fillMaxSize()
        ) {
            DeleteDialog(
                modifier = modifier,
                onClickDialogDeleteButton = {
                    deleteRoutineSetRoutine(selectedRoutineSetRoutine.id)
                },
                onClickDialogDismissButton = invisibleDeleteDialog,
            )
        }
    }
    Scaffold(
        topBar = {
            LiftBackTopBar(
                title = "루틴리스트 수정",
                onBackClickTopBar = navigateRoutineSetToRoutineSelectionInUpdateRoutineGraph
            )
        },
        snackbarHost = {
            LiftErrorSnackBar(
                modifier = modifier,
                snackbarHostState = snackbarHostState
            )
        },
        modifier = modifier.fillMaxSize()
    ) { padding ->
        Surface(
            color = LiftTheme.colorScheme.no5,
            modifier = modifier
                .fillMaxSize()
                .padding(padding)
                .noRippleClickable(
                    onClick = { focusManager.clearFocus() },
                )
        ) {
            Column(
                modifier = modifier
                    .padding(16.dp)
                    .fillMaxWidth()
                    .verticalScroll(scrollState)
            ) {

                RoutineSetPictureView(
                    modifier,
                    selectedRoutineSetRoutine,
                    navigateRoutineSetToProfilePictureInUpdateRoutineGraph
                )

                Spacer(modifier = modifier.padding(16.dp))

                RoutineSetNameView(
                    modifier,
                    selectedRoutineSetRoutine,
                    routineSetNameValidator,
                    updateRoutineSetName,
                    focusManager
                )

                Spacer(modifier = modifier.padding(9.dp))

                RoutineSetDescriptionView(
                    modifier,
                    selectedRoutineSetRoutine,
                    routineSetDescriptionValidator,
                    updateRoutineSetDescription,
                    focusManager
                )
                Spacer(modifier = modifier.padding(9.dp))


                WeekdayCardListView(
                    modifier, weekDateSelectionList, updateRoutineSetWeekday
                )
                Spacer(modifier = modifier.padding(14.dp))

                RoutineSetRoutineView(
                    modifier,
                    selectedRoutineSetRoutine,
                    removeRoutine,
                    navigateRoutineSetToFindWorkCategoryInUpdateRoutineGraph
                )
//
//                Spacer(modifier = modifier.padding(27.dp))
//                NavigationView(
//                    modifier,
//                    createRoutineCondition,
//                    createRoutineSetRoutine
//                )
            }
        }
    }
}
