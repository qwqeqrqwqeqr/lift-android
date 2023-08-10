package com.gradation.lift.feature.create_routine.find_work_category

import android.annotation.SuppressLint
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
import com.gradation.lift.feature.create_routine.routine_set.data.CreateRoutineSharedViewModel
import com.gradation.lift.model.utils.ModelDataGenerator.WorkCategory.workCategoryModel1
 import com.gradation.lift.model.model.work.WorkCategory
import com.gradation.lift.navigation.Router

@SuppressLint("UnrememberedGetBackStackEntry")
@Composable
fun CreateRoutineFindWorkCategoryRoute(
    navController: NavController,
    navigateCreateRoutineFindWorkCategoryToRoutineSet: () -> Unit,
    navigateCreateRoutineFindWorkCategoryToRoutine: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: CreateRoutineFindWorkCategoryViewModel = hiltViewModel()
) {
    val crateRoutineBackStackEntry =
        remember { navController.getBackStackEntry(Router.CREATE_ROUTINE_GRAPH_NAME) }
    val sharedViewModel: CreateRoutineSharedViewModel = hiltViewModel(crateRoutineBackStackEntry)


    val searchText = viewModel.searchText.collectAsStateWithLifecycle()
    val workPartFilterList = viewModel.workPartFilterList.collectAsStateWithLifecycle()
    val workCategoryList = viewModel.workCategoryList.collectAsStateWithLifecycle()
    val filteredWorkCategoryCount =
        viewModel.filteredWorkCategoryCount.collectAsStateWithLifecycle()

    CreateRoutineFindWorkCategoryScreen(
        modifier = modifier,
        onBackClickTopBar = navigateCreateRoutineFindWorkCategoryToRoutineSet,
        onClickWorkCategory = { workCategory ->
            sharedViewModel.updateTempWorkCategory(workCategory)
            navigateCreateRoutineFindWorkCategoryToRoutine()
        },
        searchText = searchText,
        workPartFilterList = workPartFilterList,
        workCategoryList = workCategoryList,
        filteredWorkCategoryCount = filteredWorkCategoryCount,
        updateSearchText = viewModel.updateSearchText(),
        updateSelectedWorkPartFilter = viewModel.updateSelectedWorkPartFilter()
    )

    BackHandler(onBack = navigateCreateRoutineFindWorkCategoryToRoutineSet)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateRoutineFindWorkCategoryScreen(
    modifier: Modifier = Modifier,
    onBackClickTopBar: () -> Unit,
    onClickWorkCategory: (String) -> Unit,
    searchText: State<String>,
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
                modifier=modifier,
                onClickWorkCategory=onClickWorkCategory,
                workCategoryList=workCategoryList
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
                    SelectedWorkPartFilter("전체", true),
                    SelectedWorkPartFilter("어깨", false),
                    SelectedWorkPartFilter("가슴", false),
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