package com.gradation.lift.feature.ready_work.change_order

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.gradation.lift.designsystem.component.LiftBackTopBar
import com.gradation.lift.designsystem.component.LiftButton
import com.gradation.lift.designsystem.resource.LiftIcon
import com.gradation.lift.designsystem.theme.LiftMaterialTheme
import com.gradation.lift.designsystem.theme.LiftTheme
import com.gradation.lift.feature.ready_work.change_order.data.ReadyWorkChangeOrderViewModel
import com.gradation.lift.feature.ready_work.change_order.data.RoutineSetRoutineUiState
import com.gradation.lift.test.data.TestModelDataGenerator.Routine.routineSetRoutineModelList

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReadyWorkChangeOrderRoute(
    navController: NavController,
    navigateToReadyWorkSelection: () -> Unit,
    navigateReadyWorkToWorkGraph: () -> Unit,
    selectedRoutineSetIdList: List<Int>?,
    modifier: Modifier = Modifier,
    viewModel: ReadyWorkChangeOrderViewModel = hiltViewModel(),
) {
    val routineSetRoutine by viewModel.routineSetRoutine.collectAsStateWithLifecycle()

    ReadyWorkChangeOrderScreen(
        modifier = modifier,
        onBackClickTopBar = navigateToReadyWorkSelection,
        onClickStartWork = navigateReadyWorkToWorkGraph,
        routineSetRoutine = routineSetRoutine
    )

    LaunchedEffect(key1 = true) {
        viewModel.updateSelectedRoutineSetIdList(
            selectedRoutineSetIdList ?: emptyList()
        )
    }
}

@ExperimentalMaterial3Api
@Composable
fun ReadyWorkChangeOrderScreen(
    modifier: Modifier = Modifier,
    onBackClickTopBar: () -> Unit,
    onClickStartWork: () -> Unit,
    routineSetRoutine: RoutineSetRoutineUiState,
) {
    Scaffold(
        topBar = {
            LiftBackTopBar(
                title = "루틴리스트 선택",
                onBackClickTopBar = onBackClickTopBar
            )
        },
        floatingActionButton = {
            LiftButton(
                contentPadding = PaddingValues(
                    start = 10.dp, top = 5.dp, end = 10.dp, bottom = 5.dp
                ),
                onClick = onClickStartWork,
                modifier = modifier
                    .fillMaxWidth()
                    .padding(
                        start = 20.dp,
                        end = 20.dp,
                    ),
            ) {
                Text(
                    text = "운동시작하기",
                    style = LiftTheme.typography.no3,
                    color = LiftTheme.colorScheme.no5,
                )
                Spacer(modifier = modifier.padding(2.dp))
                Icon(
                    painterResource(id = LiftIcon.ChevronRight),
                    contentDescription = null,
                    modifier = modifier
                        .align(Alignment.CenterVertically)
                        .fillMaxHeight()
                        .width(8.dp)
                )

            }
        },
        floatingActionButtonPosition = FabPosition.Center
    ) {
        Column(
            modifier = modifier
                .background(LiftTheme.colorScheme.no5)
                .padding(it)
                .padding(16.dp)
                .fillMaxSize()
        ) {
            when(routineSetRoutine){
                RoutineSetRoutineUiState.Empty -> TODO()
                is RoutineSetRoutineUiState.Fail -> TODO()
                RoutineSetRoutineUiState.Loading -> TODO()
                is RoutineSetRoutineUiState.Success -> {
                    Text(
                        text = "운동순서",
                        style = LiftTheme.typography.no3,
                        color = LiftTheme.colorScheme.no9,
                        textAlign = TextAlign.Start
                    )
                }
            }
        }
    }
}

@SuppressLint("UnrememberedMutableState")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview
fun ReadyWorkChangeOrderScreenPreview() {
    LiftMaterialTheme {
        ReadyWorkChangeOrderScreen(
            modifier = Modifier,
            onBackClickTopBar = {},
            onClickStartWork = {},
            routineSetRoutine =
            RoutineSetRoutineUiState.Success(
                routineSetRoutine = routineSetRoutineModelList
            ),
        )
    }
}