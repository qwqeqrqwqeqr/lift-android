package com.gradation.lift.feature.create_routine

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.gradation.lift.designsystem.canvas.PlusCircle
import com.gradation.lift.designsystem.component.LiftBackTopBar
import com.gradation.lift.designsystem.component.LiftButton
import com.gradation.lift.designsystem.component.LiftTextField
import com.gradation.lift.designsystem.resource.LiftIcon
import com.gradation.lift.designsystem.theme.LiftMaterialTheme
import com.gradation.lift.designsystem.theme.LiftTheme
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
@SuppressLint("UnrememberedGetBackStackEntry")
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

    CreateRoutineScreen(
        modifier = modifier,

        onBackClickTopBar = visibleCancelDialog,
        onClickProfile = navigateCreateRoutineRootToProfile,
        onAddRoutine = navigateCreateRoutineRootToFindWorkCategory,
        onClickCreateRoutine = { sharedViewModel.createRoutine() },

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

}


@OptIn(ExperimentalMaterial3Api::class, ExperimentalGlideComposeApi::class)
@Composable
internal fun CreateRoutineScreen(
    modifier: Modifier = Modifier,

    onBackClickTopBar: () -> Unit,
    onClickProfile: () -> Unit,
    onAddRoutine: () -> Unit,
    onClickCreateRoutine: () -> Unit,

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
            Column(
                modifier = modifier
                    .padding(padding)
                    .padding(16.dp)
                    .fillMaxWidth()
                    .verticalScroll(scrollState)
            ) {
                Text(
                    text = "루틴리스트 프로필",
                    style = LiftTheme.typography.no3,
                    color = LiftTheme.colorScheme.no3
                )
                Spacer(modifier = modifier.padding(8.dp))

                Box(
                    modifier = modifier
                        .align(Alignment.CenterHorizontally)
                        .background(
                            color = LiftTheme.colorScheme.no1,
                            shape = RoundedCornerShape(size = 12.dp)
                        )
                        .size(96.dp)
                        .clickable(
                            onClick = onClickProfile
                        ), contentAlignment = Alignment.Center

                ) {
                    if (picture.value.isBlank()) {
                        Image(
                            modifier = modifier.size(32.dp),
                            painter = painterResource(id = LiftIcon.Plus),
                            contentDescription = "",
                            colorFilter = ColorFilter.tint(LiftTheme.colorScheme.no6)
                        )
                    } else {
                        GlideImage(
                            model = picture.value,
                            contentDescription = "",
                            modifier = modifier.fillMaxSize()
                        )
                    }
                }
                Spacer(modifier = modifier.padding(16.dp))

                Text(
                    text = "루틴리스트 이름",
                    style = LiftTheme.typography.no3,
                    color = LiftTheme.colorScheme.no3
                )
                Spacer(modifier = modifier.padding(4.dp))
                LiftTextField(
                    value = nameText.value,
                    onValueChange = updateNameText,
                    modifier = modifier.fillMaxWidth(),
                    placeholder = {
                        Text(
                            text = "이름을 입력해주세요 (1-8 자)",
                            style = LiftTheme.typography.no6,
                        )
                    },
                    singleLine = true,
                )
                Spacer(modifier = modifier.padding(9.dp))

                Text(
                    text = "루틴리스트 설명",
                    style = LiftTheme.typography.no3,
                    color = LiftTheme.colorScheme.no3
                )
                Spacer(modifier = modifier.padding(4.dp))
                LiftTextField(
                    value = descriptionText.value,
                    onValueChange = updateDescriptionText,
                    modifier = modifier.fillMaxWidth(),
                    placeholder = {
                        Text(
                            text = "간단한 설명을 입력해주세요 (0-15 자)",
                            style = LiftTheme.typography.no6,
                        )
                    },
                    singleLine = true,
                )
                Spacer(modifier = modifier.padding(9.dp))

                Text(
                    text = "요일 선택",
                    style = LiftTheme.typography.no3,
                    color = LiftTheme.colorScheme.no3
                )
                Text(
                    text = "무슨요일에 운동을 하실건가요?",
                    style = LiftTheme.typography.no6,
                    color = LiftTheme.colorScheme.no2
                )
                Spacer(modifier = modifier.padding(7.dp))
                WeekdayCardListView(
                    weekdayCardList = weekdayCardList,
                    modifier = modifier,
                    onClickWeekDayCard = updateWeekday
                )
                Spacer(modifier = modifier.padding(14.dp))

                Text(
                    text = "루틴리스트",
                    style = LiftTheme.typography.no3,
                    color = LiftTheme.colorScheme.no3
                )
                Spacer(modifier = modifier.padding(8.dp))


                if (routine.value.isEmpty()) {
                    Box(
                        modifier = modifier
                            .fillMaxWidth()
                            .background(
                                color = LiftTheme.colorScheme.no1,
                                shape = RoundedCornerShape(size = 12.dp)
                            )
                            .padding(vertical = 32.dp),
                        contentAlignment = Alignment.Center,
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                        ) {
                            Box(
                                modifier = modifier
                                    .background(
                                        LiftTheme.colorScheme.no4,
                                        shape = RoundedCornerShape(size = 64.dp)
                                    )
                                    .size(42.dp), contentAlignment = Alignment.Center
                            ) {
                                IconButton(onClick = onAddRoutine) {
                                    Icon(
                                        modifier = modifier.size(16.dp),
                                        painter = painterResource(id = LiftIcon.Plus),
                                        contentDescription = "",
                                        tint = LiftTheme.colorScheme.no5
                                    )
                                }
                            }
                            Spacer(modifier = modifier.padding(5.dp))
                            Text(
                                text = "+ 버튼을 눌러 루틴을 추가해요",
                                style = LiftTheme.typography.no6,
                                color = LiftTheme.colorScheme.no2
                            )

                        }
                    }
                } else {
                    routine.value.forEachIndexed { index, createRoutine ->

                        Column(
                            modifier = modifier
                                .background(LiftTheme.colorScheme.no5)
                                .border(
                                    width = 1.dp,
                                    color = LiftTheme.colorScheme.no8,
                                    shape = RoundedCornerShape(size = 12.dp)
                                )
                                .padding(14.dp)
                                .fillMaxWidth()
                        ) {
                            Row(
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = createRoutine.workCategory,
                                    style = LiftTheme.typography.no3,
                                    color = LiftTheme.colorScheme.no9,
                                    modifier = modifier.weight(1f)
                                )
                                Row(
                                    modifier = modifier.weight(1f),
                                    horizontalArrangement = Arrangement.End,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Icon(
                                        painter = painterResource(LiftIcon.Trash),
                                        contentDescription = "",
                                        tint = Color.Unspecified,
                                        modifier = modifier.clickable(
                                            onClick = { onRemoveRoutineSet(createRoutine) }
                                        )
                                    )
                                    Spacer(modifier = modifier.padding(12.dp))
                                    Icon(
                                        painter = painterResource(LiftIcon.Order),
                                        contentDescription = "",
                                        tint = Color.Unspecified,
                                        modifier = modifier
                                    )
                                }
                            }
                            Spacer(
                                modifier = modifier.padding(8.dp)
                            )
                            Row(
                                horizontalArrangement = Arrangement.spacedBy(24.dp),
                                modifier = modifier.padding(horizontal = 12.dp, vertical = 8.dp)
                            ) {
                                Text(
                                    text = "Set",
                                    style = LiftTheme.typography.no3,
                                    color = LiftTheme.colorScheme.no9,
                                    textAlign = TextAlign.Center,
                                    modifier = modifier.weight(1f)
                                )
                                Text(
                                    text = "Kg",
                                    style = LiftTheme.typography.no3,
                                    color = LiftTheme.colorScheme.no9,
                                    textAlign = TextAlign.Center,
                                    modifier = modifier.weight(1f)
                                )
                                Text(
                                    text = "Reps",
                                    style = LiftTheme.typography.no3,
                                    color = LiftTheme.colorScheme.no9,
                                    textAlign = TextAlign.Center,
                                    modifier = modifier.weight(1f)
                                )
                            }
                            Spacer(modifier = modifier.padding(4.dp))
                            createRoutine.workSetList.forEachIndexed { index, workSet ->
                                Row(
                                    modifier = modifier
                                        .background(LiftTheme.colorScheme.no5)
                                        .border(
                                            width = 1.dp,
                                            color = LiftTheme.colorScheme.no8,
                                            shape = RoundedCornerShape(size = 8.dp)
                                        )
                                        .padding(horizontal = 12.dp, vertical = 8.dp)
                                        .fillMaxWidth(),
                                    horizontalArrangement = Arrangement.spacedBy(24.dp)
                                ) {
                                    Text(
                                        text = "${index + 1}",
                                        style = LiftTheme.typography.no3,
                                        color = LiftTheme.colorScheme.no2,
                                        textAlign = TextAlign.Center,
                                        modifier = modifier
                                            .weight(1f)
                                            .padding(3.dp)
                                    )

                                    Text(
                                        style = LiftTheme.typography.no3,
                                        color = LiftTheme.colorScheme.no9,
                                        text = workSet.weight.toString(),
                                        textAlign = TextAlign.Center,
                                        modifier = modifier
                                            .weight(1f)
                                            .background(
                                                color = LiftTheme.colorScheme.no1,
                                                shape = RoundedCornerShape(size = 6.dp)
                                            )
                                            .padding(3.dp)

                                    )

                                    Text(
                                        style = LiftTheme.typography.no3,
                                        color = LiftTheme.colorScheme.no9,
                                        text = workSet.repetition.toString(),
                                        textAlign = TextAlign.Center,
                                        modifier = modifier
                                            .weight(1f)
                                            .background(
                                                color = LiftTheme.colorScheme.no1,
                                                shape = RoundedCornerShape(size = 6.dp)
                                            )
                                            .padding(3.dp)

                                    )
                                }
                                Spacer(modifier = modifier.padding(2.dp))

                            }
                        }

                    }

                }




                Spacer(modifier = modifier.padding(27.dp))

                LiftButton(
                    modifier = modifier.fillMaxWidth(),
                    onClick = onClickCreateRoutine,
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
            onClickCreateRoutine = { },

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
