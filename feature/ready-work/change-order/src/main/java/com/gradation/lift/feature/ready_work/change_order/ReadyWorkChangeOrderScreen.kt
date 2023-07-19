package com.gradation.lift.feature.ready_work.change_order

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.gradation.lift.designsystem.canvas.NumberCircle
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
            when (routineSetRoutine) {
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
                    Spacer(modifier = modifier.padding(8.dp))
                    LazyColumn(
                        modifier = modifier,
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        itemsIndexed(routineSetRoutine.routineSetRoutine) { index, routineSetRoutine ->
                            Row(
                                modifier = modifier
                                    .background(LiftTheme.colorScheme.no5)
                                    .border(
                                        width = 1.dp,
                                        color = LiftTheme.colorScheme.no8,
                                        shape = RoundedCornerShape(size = 16.dp)
                                    )
                                    .padding(16.dp)
                                    .fillMaxWidth(),
                                horizontalArrangement=Arrangement.SpaceBetween
                            ) {
                                Row {
                                    NumberCircle(
                                        number = index + 1,
                                        modifier = modifier.align(CenterVertically)
                                    )
                                    Spacer(modifier = modifier.padding(8.dp))

                                    Column(
                                        horizontalAlignment = Alignment.Start,
                                        verticalArrangement = Arrangement.Center,
                                        modifier = modifier.align(CenterVertically)
                                    ) {
                                        Row {
                                            Text(
                                                text = routineSetRoutine.name,
                                                style = LiftTheme.typography.no2,
                                                color = LiftTheme.colorScheme.no9,
                                                overflow = TextOverflow.Ellipsis,
                                                textAlign = TextAlign.Start,
                                                maxLines = 1
                                            )
                                            Spacer(modifier = modifier.padding(4.dp))
                                            Icon(
                                                painter = painterResource(LiftIcon.Trash),
                                                contentDescription = "",
                                                tint = Color.Unspecified,
                                                modifier = modifier.align(CenterVertically)

                                            )
                                        }
                                        Text(
                                            text = routineSetRoutine.description,
                                            style = LiftTheme.typography.no4,
                                            color = LiftTheme.colorScheme.no9,
                                            textAlign = TextAlign.Start,
                                            overflow = TextOverflow.Ellipsis,
                                            maxLines = 1
                                        )
                                    }
                                }
                                Icon(
                                    painter = painterResource(LiftIcon.Order),
                                    contentDescription = "",
                                    tint = Color.Unspecified,
                                    modifier = modifier.align(CenterVertically)
                                )

                            }
                        }
                    }
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