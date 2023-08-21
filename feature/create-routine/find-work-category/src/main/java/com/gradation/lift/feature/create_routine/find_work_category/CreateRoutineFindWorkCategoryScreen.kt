package com.gradation.lift.feature.create_routine.find_work_category

import android.annotation.SuppressLint
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.gradation.lift.designsystem.component.LiftBackTopBar
import com.gradation.lift.designsystem.theme.LiftMaterialTheme
import com.gradation.lift.feature.create_routine.find_work_category.component.FilterView
import com.gradation.lift.feature.create_routine.find_work_category.component.SearchView
import com.gradation.lift.feature.create_routine.find_work_category.component.WorkCategoryView
import com.gradation.lift.feature.create_routine.find_work_category.data.model.WorkPartFilterSelection
import com.gradation.lift.feature.create_routine.find_work_category.data.viewmodel.CreateRoutineFindWorkCategoryViewModel
import com.gradation.lift.feature.create_routine.routine_set.data.viewmodel.CreateRoutineSharedViewModel
import com.gradation.lift.model.utils.ModelDataGenerator.WorkCategory.workCategoryModel1
import com.gradation.lift.model.model.work.WorkCategory
import com.gradation.lift.navigation.Router

@SuppressLint("UnrememberedGetBackStackEntry")
@Composable
fun CreateRoutineFindWorkCategoryRoute(
    navController: NavController,
    navigateFindWorkCategoryToRoutineSet: () -> Unit,
    navigateFindWorkCategoryToRoutine: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: CreateRoutineFindWorkCategoryViewModel = hiltViewModel(),
) {
    val crateRoutineBackStackEntry =
        remember { navController.getBackStackEntry(Router.CREATE_ROUTINE_GRAPH_NAME) }
    val sharedViewModel: CreateRoutineSharedViewModel = hiltViewModel(crateRoutineBackStackEntry)


    val searchText: String by viewModel.searchTextState.searchText.collectAsStateWithLifecycle()
    val workPartFilter: String by
    viewModel.workPartFilterState.workPartFilter.collectAsStateWithLifecycle()
    val workPartFilterList = viewModel.workPartFilterList.collectAsStateWithLifecycle()
    val workCategoryList = viewModel.workCategoryList.collectAsStateWithLifecycle()
    val filteredWorkCategoryCount =
        viewModel.filteredWorkCategoryCount.collectAsStateWithLifecycle()


    BackHandler(onBack = navigateFindWorkCategoryToRoutineSet)
}

@Composable
fun CreateRoutineFindWorkCategoryScreen(
    modifier: Modifier = Modifier,
    onBackClickTopBar: () -> Unit,
    onClickWorkCategory: (String) -> Unit,
    searchText: State<String>,
    workPartFilterList: State<List<WorkPartFilterSelection>>,
    workCategoryList: State<List<WorkCategory>>,
    filteredWorkCategoryCount: State<Int>,
    updateSearchText: (String) -> Unit,
    updateSelectedWorkPartFilter: (String) -> Unit,
) {
    Scaffold(
        topBar = {
            LiftBackTopBar(
                title = "운동부위 선택",
                onBackClickTopBar = onBackClickTopBar,
            )
        }, modifier = modifier.fillMaxSize()
    ) { paddingValues ->
        Column(modifier = modifier.padding(paddingValues)) {
            SearchView(
                modifier = modifier,
                searchText = searchText,
                workPartFilterList = workPartFilterList,
                updateSearchText = updateSearchText,
                updateSelectedWorkPartFilter = updateSelectedWorkPartFilter
            )

            FilterView(
                modifier = modifier,
                filteredWorkCategoryCount = filteredWorkCategoryCount
            )

            WorkCategoryView(
                modifier = modifier,
                onClickWorkCategory = onClickWorkCategory,
                workCategoryList = workCategoryList
            )


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
            workPartFilterList = mutableStateOf(
                listOf(
                    WorkPartFilterSelection("전체", true),
                    WorkPartFilterSelection("어깨", false),
                    WorkPartFilterSelection("가슴", false),
                )
            ),
            workCategoryList = mutableStateOf(
                listOf(
                    workCategoryModel1,
                    workCategoryModel1,
                    workCategoryModel1,
                    workCategoryModel1,
                    workCategoryModel1,
                )
            ),
            filteredWorkCategoryCount = mutableStateOf(20),
            updateSearchText = {},
            updateSelectedWorkPartFilter = { },
        )
    }
}