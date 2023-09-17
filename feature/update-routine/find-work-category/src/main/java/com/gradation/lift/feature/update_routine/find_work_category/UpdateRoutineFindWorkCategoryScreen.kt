package com.gradation.lift.feature.update_routine.find_work_category

import android.annotation.SuppressLint
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import com.gradation.lift.designsystem.component.LiftBackTopBar
import com.gradation.lift.designsystem.theme.LiftMaterialTheme
import com.gradation.lift.feature.update_routine.find_work_category.component.FilterCountView
import com.gradation.lift.feature.update_routine.find_work_category.component.FilterView
import com.gradation.lift.feature.update_routine.find_work_category.component.SearchView
import com.gradation.lift.feature.update_routine.find_work_category.component.WorkCategoryView
import com.gradation.lift.feature.update_routine.find_work_category.data.model.WorkPartFilterSelection
import com.gradation.lift.feature.update_routine.find_work_category.data.viewmodel.UpdateRoutineFindWorkCategoryViewModel
import com.gradation.lift.feature.update_routine.routine_selection.data.viewmodel.UpdateRoutineSharedViewModel
import com.gradation.lift.model.utils.ModelDataGenerator.WorkCategory.workCategoryModel1
import com.gradation.lift.model.model.work.WorkCategory
import com.gradation.lift.navigation.Router


@SuppressLint("UnrememberedGetBackStackEntry")
@Composable
fun UpdateRoutineFindWorkCategoryRoute(
    navController: NavController,
    navigateFindWorkCategoryToRoutineSetInUpdateRoutineGraph: () -> Unit,
    navigateFindWorkCategoryToRoutineInUpdateRoutineGraph: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: UpdateRoutineFindWorkCategoryViewModel = hiltViewModel(),
) {
    val updateRoutineBackStackEntry: NavBackStackEntry =
        remember { navController.getBackStackEntry(Router.UPDATE_ROUTINE_GRAPH_NAME) }
    val sharedViewModel: UpdateRoutineSharedViewModel = hiltViewModel(updateRoutineBackStackEntry)


    val searchText: String by viewModel.searchTextState.searchText.collectAsStateWithLifecycle()
    val workPartFilterList: List<WorkPartFilterSelection> by viewModel.workPartFilterList.collectAsStateWithLifecycle()
    val workCategoryList: List<WorkCategory> by viewModel.workCategoryList.collectAsStateWithLifecycle()
    val filteredWorkCategoryCount: Int by
    viewModel.filteredWorkCategoryCount.collectAsStateWithLifecycle()

    val updateSearchText: (String) -> Unit = viewModel.searchTextState.updateSearchText()
    val updateWorkPartFilter: (String) -> Unit =
        viewModel.workPartFilterState.updateWorkPartFilter()
    val updateTempWorkCategory: (String) -> Unit = sharedViewModel.updateTempWorkCategory()



    UpdateRoutineFindWorkCategoryScreen(
        modifier,
        searchText,
        workPartFilterList,
        workCategoryList,
        filteredWorkCategoryCount,
        updateSearchText,
        updateWorkPartFilter,
        updateTempWorkCategory,
        navigateFindWorkCategoryToRoutineSetInUpdateRoutineGraph,
        navigateFindWorkCategoryToRoutineInUpdateRoutineGraph
    )

    BackHandler(onBack = navigateFindWorkCategoryToRoutineSetInUpdateRoutineGraph)
}

@Composable
fun UpdateRoutineFindWorkCategoryScreen(
    modifier: Modifier = Modifier,
    searchText: String,
    workPartFilterList: List<WorkPartFilterSelection>,
    workCategoryList: List<WorkCategory>,
    filteredWorkCategoryCount: Int,
    updateSearchText: (String) -> Unit,
    updateWorkPartFilter: (String) -> Unit,
    updateTempWorkCategory: (String) -> Unit,
    navigateFindWorkCategoryToRoutineSetInUpdateRoutineGraph: () -> Unit,
    navigateFindWorkCategoryToRoutineInUpdateRoutineGraph: () -> Unit,
) {
    Scaffold(
        topBar = {
            LiftBackTopBar(
                title = "운동부위 선택",
                onBackClickTopBar = navigateFindWorkCategoryToRoutineSetInUpdateRoutineGraph,
            )
        }, modifier = modifier.fillMaxSize()
    ) { paddingValues ->
        Column(modifier = modifier.padding(paddingValues)) {
            SearchView(modifier, searchText, updateSearchText)
            FilterView(modifier, workPartFilterList, updateWorkPartFilter)
            FilterCountView(modifier, filteredWorkCategoryCount)
            WorkCategoryView(
                modifier,
                workCategoryList,
                updateTempWorkCategory,
                navigateFindWorkCategoryToRoutineInUpdateRoutineGraph
            )
        }
    }
}


@Composable
@Preview
fun UpdateRoutineFindWorkCategoryScreenPreview() {
    LiftMaterialTheme {
        UpdateRoutineFindWorkCategoryScreen(
            searchText = "",
            workPartFilterList = listOf(
                WorkPartFilterSelection("전체", true),
                WorkPartFilterSelection("어깨", false),
                WorkPartFilterSelection("가슴", false),
            ),
            workCategoryList = listOf(
                workCategoryModel1,
                workCategoryModel1,
                workCategoryModel1,
                workCategoryModel1,
                workCategoryModel1,
            ),
            filteredWorkCategoryCount = 25,
            updateSearchText = {},
            updateWorkPartFilter = {},
            updateTempWorkCategory = {},
            navigateFindWorkCategoryToRoutineSetInUpdateRoutineGraph = {},
            navigateFindWorkCategoryToRoutineInUpdateRoutineGraph = {}
        )
    }
}
