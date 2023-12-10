package com.gradation.lift.feature.createRoutine.routineSet

import android.annotation.SuppressLint
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
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
import com.gradation.lift.ui.modifier.noRippleClickable
import com.gradation.lift.designsystem.theme.LiftMaterialTheme
import com.gradation.lift.designsystem.theme.LiftTheme
import com.gradation.lift.feature.createRoutine.routineSet.component.*
import com.gradation.lift.feature.createRoutine.routineSet.component.CancelDialog
import com.gradation.lift.feature.createRoutine.routineSet.component.WeekdayCardListView
import com.gradation.lift.feature.createRoutine.routineSet.data.model.WeekdaySelection
import com.gradation.lift.feature.createRoutine.routineSet.data.state.CreateRoutineState
import com.gradation.lift.feature.createRoutine.routineSet.data.viewmodel.CreateRoutineRoutineSetViewModel
import com.gradation.lift.feature.createRoutine.routineSet.data.viewmodel.CreateRoutineSharedViewModel
import com.gradation.lift.model.model.date.Weekday
import com.gradation.lift.model.model.routine.CreateRoutine
import com.gradation.lift.model.utils.ModelDataGenerator.RoutineSetRoutine.createRoutineModel
import com.gradation.lift.navigation.Router
import com.gradation.lift.ui.utils.DevicePreview



@SuppressLint("UnrememberedGetBackStackEntry", "StateFlowValueCalledInComposition")
@Composable
internal fun CreateRoutineRoutineSetRoute(
    navController: NavController,
    navigateRoutineSetToFindWorkCategoryInCreateRoutineGraph: () -> Unit,
    navigateRoutineSetToProfilePictureInCreateRoutineGraph: () -> Unit,
    navigateCreateRoutineGraphToHomeGraph: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: CreateRoutineRoutineSetViewModel = hiltViewModel(),
) {
    val crateRoutineBackStackEntry: NavBackStackEntry =
        remember { navController.getBackStackEntry(Router.CREATE_ROUTINE_GRAPH_NAME) }
    val sharedViewModel: CreateRoutineSharedViewModel = hiltViewModel(crateRoutineBackStackEntry)

    val routineSetName: String by sharedViewModel.routineSetName.collectAsStateWithLifecycle()
    val routineSetDescription: String by sharedViewModel.routineSetDescription.collectAsStateWithLifecycle()
    val routineSetNameValidator: Validator by sharedViewModel.routineSetNameValidator.collectAsStateWithLifecycle()
    val routineSetDescriptionValidator: Validator by sharedViewModel.routineSetDescriptionValidator.collectAsStateWithLifecycle()

    val routineSetPicture: String by sharedViewModel.routineSetPicture.collectAsStateWithLifecycle()
    val routineSetRoutine: List<CreateRoutine> by sharedViewModel.routineSetRoutine.collectAsStateWithLifecycle()
    val weekdaySelectionList: List<WeekdaySelection> by sharedViewModel.weekdaySelectionList.collectAsStateWithLifecycle()
    val createRoutineCondition: Boolean by
    sharedViewModel.createRoutineCondition.collectAsStateWithLifecycle()
    val createRoutineState: CreateRoutineState by sharedViewModel.createRoutineState.collectAsStateWithLifecycle()


    val onVisibleCancelDialog: Boolean by viewModel.onVisibleCancelDialog.collectAsStateWithLifecycle()
    val visibleCancelDialog: () -> Unit = viewModel.visibleCancelDialog()
    val inVisibleCancelDialog: () -> Unit = viewModel.invisibleCancelDialog()

    val updateRoutineSetName: (String) -> Unit = sharedViewModel.updateRoutineSetName()
    val updateRoutineSetDescription: (String) -> Unit =
        sharedViewModel.updateRoutineSetDescription()
    val updateRoutineSetWeekday: (Weekday) -> Unit = sharedViewModel.updateRoutineSetWeekday()
    val removeRoutine: (CreateRoutine) -> Unit = sharedViewModel.removeRoutine()
    val updateCreateRoutineState: (CreateRoutineState) -> Unit =
        sharedViewModel.updateCreateRoutineState()
    val createRoutineSetRoutine: () -> Unit = sharedViewModel.createRoutineSetRoutine()

    val scrollState: ScrollState = rememberScrollState()
    val snackbarHostState: SnackbarHostState by remember { mutableStateOf(SnackbarHostState()) }
    val focusManager: FocusManager = LocalFocusManager.current

    CreateRoutineRoutineSetScreen(
        modifier,
        routineSetName,
        routineSetDescription,
        routineSetNameValidator,
        routineSetDescriptionValidator,
        routineSetPicture,
        routineSetRoutine,
        weekdaySelectionList,
        createRoutineCondition,
        onVisibleCancelDialog,
        visibleCancelDialog,
        inVisibleCancelDialog,
        updateRoutineSetName,
        updateRoutineSetDescription,
        updateRoutineSetWeekday,
        removeRoutine,
        createRoutineSetRoutine,
        navigateRoutineSetToFindWorkCategoryInCreateRoutineGraph,
        navigateRoutineSetToProfilePictureInCreateRoutineGraph,
        navigateCreateRoutineGraphToHomeGraph,
        scrollState,
        snackbarHostState,
        focusManager
    )


    when (val createRoutineStateResult = createRoutineState) {
        is CreateRoutineState.Fail -> {
            LaunchedEffect(true) {
                snackbarHostState.showSnackbar(
                    message = createRoutineStateResult.message, duration = SnackbarDuration.Short
                )
                updateCreateRoutineState(CreateRoutineState.None)
            }
        }

        CreateRoutineState.None -> {}
        CreateRoutineState.Success -> {
            LaunchedEffect(true) {
                navigateCreateRoutineGraphToHomeGraph()
            }
        }
    }

    BackHandler(
        onBack = if (routineSetRoutine.isEmpty()) {
            navigateCreateRoutineGraphToHomeGraph
        } else {
            visibleCancelDialog
        }
    )

}


@Composable
internal fun CreateRoutineRoutineSetScreen(
    modifier: Modifier = Modifier,
    routineSetName: String,
    routineSetDescription: String,
    routineSetNameValidator: Validator,
    routineSetDescriptionValidator: Validator,
    routineSetPicture: String,
    routineSetRoutine: List<CreateRoutine>,
    weekdaySelectionList: List<WeekdaySelection>,
    createRoutineCondition: Boolean,
    onVisibleCancelDialog: Boolean,
    visibleCancelDialog: () -> Unit,
    inVisibleCancelDialog: () -> Unit,
    updateRoutineSetName: (String) -> Unit,
    updateRoutineSetDescription: (String) -> Unit,
    updateRoutineSetWeekday: (Weekday) -> Unit,
    removeRoutine: (CreateRoutine) -> Unit,
    createRoutineSetRoutine: () -> Unit,
    navigateRoutineSetToFindWorkCategoryInCreateRoutineGraph: () -> Unit,
    navigateRoutineSetToProfilePictureInCreateRoutineGraph: () -> Unit,
    navigateCreateRoutineGraphToHomeGraph: () -> Unit,
    scrollState: ScrollState,
    snackbarHostState: SnackbarHostState,
    focusManager: FocusManager,
) {
    if (onVisibleCancelDialog) {
        Surface(
            color = LiftTheme.colorScheme.no5.copy(alpha = 0.7f),
            modifier = modifier.fillMaxSize()
        ) {
            CancelDialog(
                onClickDialogSuspendButton = navigateCreateRoutineGraphToHomeGraph,
                onClickDialogDismissButton = inVisibleCancelDialog,
            )
        }
    }
    Scaffold(
        topBar = {
            LiftBackTopBar(
                title = "루틴 만들기",
                onBackClickTopBar = if (routineSetRoutine.isEmpty()) {
                    navigateCreateRoutineGraphToHomeGraph
                } else {
                    visibleCancelDialog
                },
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
                    navigateRoutineSetToProfilePictureInCreateRoutineGraph,
                    routineSetPicture
                )

                Spacer(modifier = modifier.padding(16.dp))

                RoutineSetNameView(
                    modifier,
                    routineSetName,
                    routineSetNameValidator,
                    updateRoutineSetName,
                    focusManager
                )

                Spacer(modifier = modifier.padding(9.dp))

                RoutineSetDescriptionView(
                    modifier,
                    routineSetDescription,
                    routineSetDescriptionValidator,
                    updateRoutineSetDescription,
                    focusManager
                )
                Spacer(modifier = modifier.padding(9.dp))


                WeekdayCardListView(
                    modifier, weekdaySelectionList, updateRoutineSetWeekday
                )
                Spacer(modifier = modifier.padding(14.dp))

                RoutineSetRoutineView(
                    modifier,
                    routineSetRoutine,
                    removeRoutine,
                    navigateRoutineSetToFindWorkCategoryInCreateRoutineGraph
                )

                Spacer(modifier = modifier.padding(27.dp))
                NavigationView(
                    modifier,
                    createRoutineCondition,
                    createRoutineSetRoutine
                )
            }
        }
    }
}


@Composable
@DevicePreview
fun CreateRoutineRoutineSetScreenPreview() {
    LiftMaterialTheme {
        CreateRoutineRoutineSetScreen(
            routineSetName = "",
            routineSetDescription = "",
            routineSetPicture = "",
            routineSetNameValidator = Validator(false, "실패"),
            routineSetDescriptionValidator = Validator(false, "실패"),
            routineSetRoutine = listOf(createRoutineModel, createRoutineModel, createRoutineModel),
            weekdaySelectionList = listOf(
                WeekdaySelection(Weekday.Monday()),
                WeekdaySelection(Weekday.Tuesday()),
                WeekdaySelection(Weekday.Wednesday()),
                WeekdaySelection(Weekday.Thursday()),
                WeekdaySelection(Weekday.Friday()),
                WeekdaySelection(Weekday.Saturday()),
                WeekdaySelection(Weekday.Sunday()),
            ),
            createRoutineCondition = true,
            onVisibleCancelDialog = false,
            visibleCancelDialog = { },
            inVisibleCancelDialog = { },
            updateRoutineSetName = { },
            updateRoutineSetDescription = { },
            updateRoutineSetWeekday = { },
            removeRoutine = { },
            createRoutineSetRoutine = { },
            navigateRoutineSetToFindWorkCategoryInCreateRoutineGraph = { },
            navigateRoutineSetToProfilePictureInCreateRoutineGraph = { },
            navigateCreateRoutineGraphToHomeGraph = { },
            scrollState = rememberScrollState(),
            snackbarHostState = SnackbarHostState(),
            focusManager = LocalFocusManager.current
        )
    }
}
