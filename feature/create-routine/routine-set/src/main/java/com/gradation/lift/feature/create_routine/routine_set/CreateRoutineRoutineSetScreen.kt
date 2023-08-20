package com.gradation.lift.feature.create_routine.routine_set

import android.annotation.SuppressLint
import android.os.Build
import androidx.activity.compose.BackHandler
import androidx.annotation.RequiresApi
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import com.gradation.lift.feature.create_routine.routine_set.component.RoutineListView
import com.gradation.lift.designsystem.component.LiftBackTopBar
import com.gradation.lift.designsystem.component.LiftButton
import com.gradation.lift.designsystem.component.LiftOutlineButton
import com.gradation.lift.designsystem.resource.LiftIcon
import com.gradation.lift.designsystem.theme.LiftMaterialTheme
import com.gradation.lift.designsystem.theme.LiftTheme
import com.gradation.lift.feature.create_routine.routine_set.component.CancelDialog
import com.gradation.lift.feature.create_routine.routine_set.component.WeekdayCardListView
import com.gradation.lift.feature.create_routine.routine_set.component.DescriptionView
import com.gradation.lift.feature.create_routine.routine_set.component.NameView
import com.gradation.lift.feature.create_routine.routine_set.component.ProfileView
import com.gradation.lift.feature.create_routine.routine_set.data.model.WeekdaySelection
import com.gradation.lift.feature.create_routine.routine_set.data.state.CreateRoutineState
import com.gradation.lift.feature.create_routine.routine_set.data.viewmodel.CreateRoutineRoutineSetViewModel
import com.gradation.lift.feature.create_routine.routine_set.data.viewmodel.CreateRoutineSharedViewModel
import com.gradation.lift.feature.create_routine.routine_set.data.viewmodel.WeekdayCard
import com.gradation.lift.model.model.common.Weekday
import com.gradation.lift.model.model.routine.CreateRoutine
import com.gradation.lift.model.model.work.WorkSet
import com.gradation.lift.navigation.Router
import com.gradation.lift.ui.utils.DevicePreview


@RequiresApi(Build.VERSION_CODES.O)
@SuppressLint("UnrememberedGetBackStackEntry", "StateFlowValueCalledInComposition")
@Composable
internal fun CreateRoutineRoutineSetRoute(
    navController: NavController,
    navigateRoutineSetToFindWorkCategory: () -> Unit,
    navigateRoutineSetToProfile: () -> Unit,
    navigateCreateRoutineGraphToHomeGraph: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: CreateRoutineRoutineSetViewModel = hiltViewModel(),
) {
    val crateRoutineBackStackEntry: NavBackStackEntry =
        remember { navController.getBackStackEntry(Router.CREATE_ROUTINE_GRAPH_NAME) }
    val sharedViewModel: CreateRoutineSharedViewModel = hiltViewModel(crateRoutineBackStackEntry)

    val name: String by sharedViewModel.routineSetName.collectAsStateWithLifecycle()
    val description: String by sharedViewModel.routineSetDescription.collectAsStateWithLifecycle()
    val picture: String by sharedViewModel.routineSetPicture.collectAsStateWithLifecycle()
    val weekdaySelectionList: List<WeekdaySelection> by sharedViewModel.weekdaySelectionList.collectAsStateWithLifecycle()
    val routine: List<CreateRoutine> by sharedViewModel.routineSetRoutine.collectAsStateWithLifecycle()
    val createRoutineCondition: Boolean by
    sharedViewModel.createRoutineCondition.collectAsStateWithLifecycle()
    val createRoutineState: CreateRoutineState by sharedViewModel.createRoutineState.collectAsStateWithLifecycle()


    val onVisibleCancelDialog: Boolean by viewModel.onVisibleCancelDialog.collectAsStateWithLifecycle()
    val visibleCancelDialog: () -> Unit = viewModel.visibleCancelDialog()
    val inVisibleCancelDialog: () -> Unit = viewModel.invisibleCancelDialog()

    val updateName: (String) -> Unit = sharedViewModel.updateName()
    val updateDescription: (String) -> Unit = sharedViewModel.updateDescription()
    val updateWeekday: (Weekday) -> Unit = sharedViewModel.updateWeekday()
    val removeRoutine: (CreateRoutine) -> Unit = sharedViewModel.removeRoutine()
    val updateCreateRoutineState: (CreateRoutineState) -> Unit =
        sharedViewModel.updateCreateRoutineState()
    val createRoutineSet: () -> Unit = sharedViewModel.createRoutineSet()


    val scrollState: ScrollState = rememberScrollState()
    val snackbarHostState: SnackbarHostState by remember { mutableStateOf(SnackbarHostState()) }
    val focusManager: FocusManager = LocalFocusManager.current

    CreateRoutineRoutineSetScreen(
        modifier = modifier,

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
            navigateCreateRoutineGraphToHomeGraph()
        }
    }

    BackHandler(onBack = visibleCancelDialog)

}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun CreateRoutineRoutineSetScreen(
    modifier: Modifier = Modifier,

    ) {
    if (onVisibleCancelDialog.value) {
        Surface(
            color = LiftTheme.colorScheme.no23, modifier = modifier.fillMaxSize()
        ) {
            CancelDialog(
                onClickDialogSuspendButton = onClickCancelDialogSuspend,
                onClickDialogDismissButton = onClickCancelDialogDismiss,
            )
        }
    } else {
        Scaffold(
            topBar = {
                LiftBackTopBar(
                    title = "루틴리스트 만들기",
                    onBackClickTopBar = onBackClickTopBar,
                )
            }, modifier = modifier.fillMaxSize()
        ) { padding ->
            Surface(
                color = LiftTheme.colorScheme.no5,
                modifier = modifier.fillMaxSize()
            ) {
                Column(
                    modifier = modifier
                        .padding(padding)
                        .padding(16.dp)
                        .fillMaxWidth()
                        .verticalScroll(scrollState)
                ) {

                    ProfileView(
                        modifier.align(Alignment.CenterHorizontally),
                        onClickProfile,
                        picture
                    )

                    Spacer(modifier = modifier.padding(16.dp))

                    NameView(
                        modifier = modifier,
                        nameText = nameText,
                        updateNameText = updateNameText
                    )

                    Spacer(modifier = modifier.padding(9.dp))

                    DescriptionView(
                        modifier = modifier,
                        descriptionText = descriptionText,
                        updateDescriptionText = updateDescriptionText
                    )
                    Spacer(modifier = modifier.padding(9.dp))


                    WeekdayCardListView(
                        weekdayCardList = weekdayCardList,
                        modifier = modifier,
                        onClickWeekDayCard = updateWeekday
                    )
                    Spacer(modifier = modifier.padding(14.dp))

                    Row(
                        modifier = modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            modifier = modifier.align(Alignment.CenterVertically),
                            text = "루틴리스트",
                            style = LiftTheme.typography.no3,
                            color = LiftTheme.colorScheme.no3
                        )
                        if (routine.value.isNotEmpty()) {
                            LiftOutlineButton(
                                modifier = modifier
                                    .height(32.dp),
                                contentPadding = PaddingValues(
                                    start = 15.dp, top = 0.dp, end = 15.dp, bottom = 0.dp
                                ),
                                onClick = onAddRoutine,
                            ) {
                                Text(
                                    text = "추가",
                                    style = LiftTheme.typography.no5,
                                    color = LiftTheme.colorScheme.no4,
                                )
                                Spacer(modifier = modifier.padding(2.dp))
                                Icon(
                                    painterResource(id = LiftIcon.Plus),
                                    contentDescription = null,
                                )
                            }
                        }
                    }

                    Spacer(modifier = modifier.padding(8.dp))

                    RoutineListView(
                        modifier = modifier,
                        routine = routine,
                        onAddRoutine = onAddRoutine,
                        onRemoveRoutineSet = onRemoveRoutineSet
                    )

                    Spacer(modifier = modifier.padding(27.dp))

                    LiftButton(
                        modifier = modifier.fillMaxWidth(),
                        onClick = onClickCreateRoutineSet,
                        enabled = enabledCreateRoutine.value
                    ) {
                        Text(
                            text = "생성하기",
                            style = LiftTheme.typography.no3,
                            color = LiftTheme.colorScheme.no5,
                        )
                    }


                }
            }
        }

    }
}


@SuppressLint("UnrememberedMutableState")
@Composable
@DevicePreview
fun CreateRoutineRoutineSetScreenPreview() {
    LiftMaterialTheme {
        CreateRoutineRoutineSetScreen(
            modifier = Modifier,

            )
    }
}
