package com.gradation.lift.feature.create_routine.find_work_category

import android.annotation.SuppressLint
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.gradation.lift.designsystem.component.LiftBackTopBar
import com.gradation.lift.designsystem.component.LiftFilterChip
import com.gradation.lift.designsystem.component.LiftSearchTextField
import com.gradation.lift.designsystem.resource.LiftIcon
import com.gradation.lift.designsystem.theme.LiftMaterialTheme
import com.gradation.lift.designsystem.theme.LiftTheme
import com.gradation.lift.feature.create_routine.data.CreateRoutineSharedViewModel
import com.gradation.lift.model.work.WorkCategory
import com.gradation.lift.navigation.Router

@SuppressLint("UnrememberedGetBackStackEntry")
@Composable
fun CreateRoutineFindWorkCategoryRoute(
    navController: NavController,
    navigateCreateRoutineFindWorkCategoryToRoot: () -> Unit,
    navigateCreateRoutineFindWorkCategoryToRoutine: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: CreateRoutineFindWorkCategoryViewModel = hiltViewModel()
) {
    val crateRoutineBackStackEntry =
        remember { navController.getBackStackEntry(Router.CREATE_ROUTINE_GRAPH_NAME) }
    val sharedViewModel: CreateRoutineSharedViewModel = hiltViewModel(crateRoutineBackStackEntry)


    val searchText = viewModel.searchText.collectAsStateWithLifecycle()
    val selectedWorkPartFilter = viewModel.selectedWorkPartFilter.collectAsStateWithLifecycle()
    val workPartFilterList = viewModel.workPartFilterList.collectAsStateWithLifecycle()
    val workCategoryList = viewModel.workCategoryList.collectAsStateWithLifecycle()
    val filteredWorkCategoryCount =
        viewModel.filteredWorkCategoryCount.collectAsStateWithLifecycle()

    CreateRoutineFindWorkCategoryScreen(
        modifier = modifier,
        onBackClickTopBar = navigateCreateRoutineFindWorkCategoryToRoot,
        onClickWorkCategory = { workCategory ->
            sharedViewModel.updateTempRoutineWorkCategory(workCategory)
            navigateCreateRoutineFindWorkCategoryToRoutine()
        },
        searchText = searchText,
        selectedWorkPartFilter = selectedWorkPartFilter,
        workPartFilterList = workPartFilterList,
        workCategoryList = workCategoryList,
        filteredWorkCategoryCount = filteredWorkCategoryCount,
        updateSearchText = viewModel.updateSearchText(),
        updateSelectedWorkPartFilter = viewModel.updateSelectedWorkPartFilter()
    )

    BackHandler(onBack = navigateCreateRoutineFindWorkCategoryToRoot)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateRoutineFindWorkCategoryScreen(
    modifier: Modifier = Modifier,
    onBackClickTopBar: () -> Unit,
    onClickWorkCategory: (String) -> Unit,
    searchText: State<String>,
    selectedWorkPartFilter: State<String>,
    workPartFilterList: State<List<SelectedWorkPartFilter>>,
    workCategoryList: State<List<WorkCategory>>,
    filteredWorkCategoryCount: State<Int>,
    updateSearchText: (String) -> Unit,
    updateSelectedWorkPartFilter: (String) -> Unit
) {
    Scaffold(
        topBar = {
            LiftBackTopBar(
                title = "운동부위 선택",
                onBackClickTopBar = onBackClickTopBar,
            )
        }, modifier = modifier.fillMaxSize()
    ) { padding ->
        Column() {
            Surface(
                color = LiftTheme.colorScheme.no5,
            ) {
                Column(
                    modifier = modifier
                        .padding(padding)
                        .padding(16.dp,16.dp,16.dp,0.dp)
                ) {
                    LiftSearchTextField(
                        modifier = modifier.fillMaxWidth(),
                        value = searchText.value,
                        onValueChange = updateSearchText,
                        placeholder = {
                            Text(
                                text = "찾으시는 운동을 검색해주세요",
                                color = LiftTheme.colorScheme.no2,
                                style = LiftTheme.typography.no6,
                            )
                        }
                    )
                    LazyRow(
                        horizontalArrangement = Arrangement.spacedBy(2.dp),
                    ) {
                        items(workPartFilterList.value) {
                            LiftFilterChip(
                                modifier= modifier.padding(vertical =  8.dp),
                                text = it.workPart, selected = it.selected,
                                onClick = { updateSelectedWorkPartFilter(it.workPart) }
                            )
                        }
                    }
                }
            }

            Surface(
                color = LiftTheme.colorScheme.no17,
                modifier = modifier.fillMaxWidth()
            ) {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = modifier.padding(horizontal = 16.dp, vertical = 2.dp)
                ) {
                    Text(
                        text = buildAnnotatedString {
                            append("총 ")
                            withStyle(
                                style = SpanStyle(
                                    fontWeight = FontWeight(700)
                                ),
                            ) {
                                append("${filteredWorkCategoryCount.value}개")
                            }
                            append("의 운동")
                        },
                        style = LiftTheme.typography.no6,
                        color = LiftTheme.colorScheme.no9,
                    )
                    IconButton(
                        modifier = modifier
                            .size(24.dp),

                        onClick = {}) {
                        Icon(
                            modifier = modifier.fillMaxSize(),
                            painter = painterResource(LiftIcon.Filter),
                            contentDescription = "",
                            tint = Color.Unspecified,
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
fun CreateRoutineFindWorkCategoryScreenPreview() {
    LiftMaterialTheme {
        CreateRoutineFindWorkCategoryScreen(
            modifier = Modifier,
            onBackClickTopBar = {},
            onClickWorkCategory = {},
            searchText = mutableStateOf(""),
            selectedWorkPartFilter = mutableStateOf(""),
            workPartFilterList = mutableStateOf(
                listOf(
                    SelectedWorkPartFilter("전체", true),
                    SelectedWorkPartFilter("어깨", false),
                    SelectedWorkPartFilter("가슴", false),
                )
            ),
            workCategoryList = mutableStateOf(emptyList()),
            filteredWorkCategoryCount = mutableStateOf(20),
            updateSearchText = {},
            updateSelectedWorkPartFilter = { },
        )
    }
}