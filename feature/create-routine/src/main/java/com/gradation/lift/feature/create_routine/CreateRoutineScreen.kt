package com.gradation.lift.feature.create_routine

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.gradation.lift.designsystem.component.LiftBackTopBar
import com.gradation.lift.designsystem.component.LiftButton
import com.gradation.lift.designsystem.component.LiftTextField
import com.gradation.lift.designsystem.theme.LiftMaterialTheme
import com.gradation.lift.designsystem.theme.LiftTheme
import com.gradation.lift.feature.create_routine.component.CancelDialog
import com.gradation.lift.feature.create_routine.data.CreateRoutineSharedViewModel
import com.gradation.lift.feature.create_routine.data.CreateRoutineViewModel
import com.gradation.lift.model.common.Weekday
import com.gradation.lift.model.routine.CreateRoutine
import com.gradation.lift.navigation.Router


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

    val crateRoutineBackStackEntry =
        remember { navController.getBackStackEntry(Router.CREATE_ROUTINE_GRAPH_NAME) }
    val sharedViewModel: CreateRoutineSharedViewModel = hiltViewModel(crateRoutineBackStackEntry)

    val name = sharedViewModel.name.collectAsStateWithLifecycle()
    val description = sharedViewModel.description.collectAsStateWithLifecycle()
    val picture = sharedViewModel.picture.collectAsStateWithLifecycle()
    val weekday = sharedViewModel.weekday.collectAsStateWithLifecycle()
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

        weekday = weekday,
        updateWeekday = sharedViewModel.updateWeekday(),

        routine = routine,
        onRemoveRoutineSet = sharedViewModel.removeRoutineSet(),

        enabledCreateRoutine = createRoutineCondition
    )

}


@OptIn(ExperimentalMaterial3Api::class)
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

    weekday: State<List<Weekday>>,
    updateWeekday: (Weekday) -> Unit,

    routine: State<List<CreateRoutine>>,
    onRemoveRoutineSet: (CreateRoutine) -> Unit,

    enabledCreateRoutine: State<Boolean>,
) {
    if (onVisibleCancelDialog.value) {
        Surface(
            color = LiftTheme.colorScheme.no8,
            modifier = modifier.fillMaxSize()
        ) {
            CancelDialog(
                onClickDialogSuspendButton = onClickCancelDialogSuspend,
                onClickDialogDismissButton = onClickCancelDialogDismiss,
            )
        }
    } else {
        Surface(color = MaterialTheme.colorScheme.surface) {
            Scaffold(
                topBar = {
                    LiftBackTopBar(
                        title = "루틴리스트 만들기",
                        onBackClickTopBar = onBackClickTopBar,
                    )
                },
            ) { it ->
                Column(
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(it)
                        .padding(16.dp)
                ) {

                    Text(
                        text = "루틴리스트 프로필",
                        style = LiftTheme.typography.no3,
                        color = LiftTheme.colorScheme.no3
                    )

                    Text(
                        text = "루틴리스트 이름",
                        style = LiftTheme.typography.no3,
                        color = LiftTheme.colorScheme.no3
                    )
                    Text(
                        text = "루틴리스트 설명",
                        style = LiftTheme.typography.no3,
                        color = LiftTheme.colorScheme.no3
                    )
                    Text(
                        text = "요일 선택",
                        style = LiftTheme.typography.no3,
                        color = LiftTheme.colorScheme.no3
                    )
                    Text(
                        text = "루틴리스트",
                        style = LiftTheme.typography.no3,
                        color = LiftTheme.colorScheme.no3
                    )






                    LiftTextField(
                        value = nameText.value,
                        onValueChange = updateNameText,
                        modifier = modifier.fillMaxWidth(),
                        placeholder = {
                            Text(
                                text = "왜 안돼",
                                style = LiftTheme.typography.no6,
                            )
                        },
                        singleLine = true,
                    )


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
}


@SuppressLint("UnrememberedMutableState")
@Composable
@Preview
fun CreateRoutineRoutineSetScreen() {
    LiftMaterialTheme {
        CreateRoutineScreen(
            modifier = Modifier,

            onBackClickTopBar = { },
            onClickProfile = {},
            onAddRoutine = {},
            onClickCreateRoutine = { },

            onVisibleCancelDialog = mutableStateOf(true),
            onClickCancelDialogSuspend = { },
            onClickCancelDialogDismiss = { },


            picture = mutableStateOf(""),

            nameText = mutableStateOf(""),
            updateNameText = {},

            descriptionText = mutableStateOf(""),
            updateDescriptionText = {},

            weekday = mutableStateOf(emptyList<Weekday>()),
            updateWeekday = {},

            routine = mutableStateOf(emptyList()),
            onRemoveRoutineSet = {},

            enabledCreateRoutine = mutableStateOf(true)
        )
    }
}
