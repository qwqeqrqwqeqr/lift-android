package com.gradation.lift.feature.create_routine

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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.gradation.lift.feature.create_routine.component.RoutineListView
import com.gradation.lift.designsystem.component.LiftBackTopBar
import com.gradation.lift.designsystem.component.LiftButton
import com.gradation.lift.designsystem.component.LiftOutlineButton
import com.gradation.lift.designsystem.resource.LiftIcon
import com.gradation.lift.designsystem.theme.LiftMaterialTheme
import com.gradation.lift.designsystem.theme.LiftTheme
import com.gradation.lift.feature.create_routine.component.*
import com.gradation.lift.feature.create_routine.component.CancelDialog
import com.gradation.lift.feature.create_routine.component.WeekdayCardListView
import com.gradation.lift.feature.create_routine.data.CreateRoutineSharedViewModel
import com.gradation.lift.feature.create_routine.data.CreateRoutineViewModel
import com.gradation.lift.feature.create_routine.data.WeekdayCard
import com.gradation.lift.model.common.Weekday
import com.gradation.lift.model.routine.CreateRoutine
import com.gradation.lift.model.work.WorkSet
import com.gradation.lift.navigation.Router
import com.gradation.lift.ui.utils.DevicePreview


@RequiresApi(Build.VERSION_CODES.O)
@SuppressLint("UnrememberedGetBackStackEntry", "StateFlowValueCalledInComposition")
@Composable
internal fun CreateRoutineRoute(
    navController: NavController,
    navigateCreateRoutineRootToFindWorkCategory: () -> Unit,
    navigateCreateRoutineRootToProfile: () -> Unit,
    navigateCreateRoutineToMain: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: CreateRoutineViewModel = hiltViewModel(),
) {
    val scrollState: ScrollState = rememberScrollState()
    val crateRoutineBackStackEntry =
        remember { navController.getBackStackEntry(Router.CREATE_ROUTINE_GRAPH_NAME) }
    val sharedViewModel: CreateRoutineSharedViewModel = hiltViewModel(crateRoutineBackStackEntry)

    val name = sharedViewModel.name.collectAsStateWithLifecycle()
    val description = sharedViewModel.description.collectAsStateWithLifecycle()
    val picture = sharedViewModel.picture.collectAsStateWithLifecycle()
    val weekdayCardList = sharedViewModel.weekdayCardList.collectAsStateWithLifecycle()
    val routine = sharedViewModel.routine.collectAsStateWithLifecycle()
    val createRoutineCondition =
        sharedViewModel.createRoutineCondition.collectAsStateWithLifecycle()


    val onVisibleCancelDialog = viewModel.onVisibleCancelDialog.collectAsStateWithLifecycle()
    val visibleCancelDialog = viewModel.visibleCancelDialog()
    val inVisibleCancelDialog = viewModel.invisibleCancelDialog()

    val navigationCondition = sharedViewModel.navigationCondition

    CreateRoutineScreen(
        modifier = modifier,

        onBackClickTopBar = visibleCancelDialog,
        onClickProfile = navigateCreateRoutineRootToProfile,
        onAddRoutine = navigateCreateRoutineRootToFindWorkCategory,
        onClickCreateRoutineSet = { sharedViewModel.createRoutine() },

        onVisibleCancelDialog = onVisibleCancelDialog,
        onClickCancelDialogSuspend = navigateCreateRoutineToMain,
        onClickCancelDialogDismiss = inVisibleCancelDialog,


        picture = picture,

        nameText = name,
        updateNameText = sharedViewModel.updateName(),

        descriptionText = description,
        updateDescriptionText = sharedViewModel.updateDescription(),

        weekdayCardList = weekdayCardList,
        updateWeekday = sharedViewModel.updateWeekday(),

        routine = routine,
        onRemoveRoutineSet = sharedViewModel.removeRoutineSet(),

        enabledCreateRoutine = createRoutineCondition,

        scrollState = scrollState
    )

    LaunchedEffect(true) {
        if (navigationCondition.value) {
            navigateCreateRoutineToMain()
        }
    }
    BackHandler(onBack = visibleCancelDialog)

}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun CreateRoutineScreen(
    modifier: Modifier = Modifier,

    onBackClickTopBar: () -> Unit,
    onClickProfile: () -> Unit,
    onAddRoutine: () -> Unit,
    onClickCreateRoutineSet: () -> Unit,

    onVisibleCancelDialog: State<Boolean>,
    onClickCancelDialogSuspend: () -> Unit,
    onClickCancelDialogDismiss: () -> Unit,

    picture: State<String>,

    nameText: State<String>,
    updateNameText: (String) -> Unit,

    descriptionText: State<String>,
    updateDescriptionText: (String) -> Unit,

    weekdayCardList: State<List<WeekdayCard>>,
    updateWeekday: (Weekday) -> Unit,

    routine: State<List<CreateRoutine>>,
    onRemoveRoutineSet: (CreateRoutine) -> Unit,

    enabledCreateRoutine: State<Boolean>,
    scrollState: ScrollState,
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
fun CreateRoutineRoutineSetScreen() {
    LiftMaterialTheme {
        CreateRoutineScreen(
            modifier = Modifier,

            onBackClickTopBar = { },
            onClickProfile = {},
            onAddRoutine = {},
            onClickCreateRoutineSet = { },

            onVisibleCancelDialog = mutableStateOf(false),
            onClickCancelDialogSuspend = { },
            onClickCancelDialogDismiss = { },


            picture = mutableStateOf(""),

            nameText = mutableStateOf(""),
            updateNameText = {},

            descriptionText = mutableStateOf(""),
            updateDescriptionText = {},

            weekdayCardList = mutableStateOf(
                listOf(
                    WeekdayCard(weekday = Weekday.Monday(), selected = false),
                    WeekdayCard(weekday = Weekday.Tuesday(), selected = false),
                    WeekdayCard(weekday = Weekday.Wednesday(), selected = false),
                    WeekdayCard(weekday = Weekday.Thursday(), selected = false),
                    WeekdayCard(weekday = Weekday.Friday(), selected = false),
                    WeekdayCard(weekday = Weekday.Saturday(), selected = false),
                    WeekdayCard(weekday = Weekday.Sunday(), selected = false)
                )
            ),
            updateWeekday = {},

            routine = mutableStateOf(
                listOf(
                    CreateRoutine(
                        workCategory = "바벨로우", workSetList = listOf(
                            WorkSet(30f, 12),
                            WorkSet(30f, 12),
                            WorkSet(30f, 12),
                            WorkSet(30f, 12),
                            WorkSet(30f, 12),
                        )
                    )
                )
            ),
            onRemoveRoutineSet = {},

            enabledCreateRoutine = mutableStateOf(true),
            scrollState = rememberScrollState()
        )
    }
}
