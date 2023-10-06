package com.gradation.lift.feature.update_routine.find_work_category.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gradation.lift.designsystem.extensions.noRippleClickable
import com.gradation.lift.designsystem.theme.LiftMaterialTheme
import com.gradation.lift.designsystem.theme.LiftTheme
import com.gradation.lift.feature.update_routine.find_work_category.UpdateRoutineFindWorkCategoryScreen
import com.gradation.lift.feature.update_routine.find_work_category.data.model.WorkPartFilterSelection
import com.gradation.lift.model.model.work.WorkCategory
import com.gradation.lift.model.utils.ModelDataGenerator


@Composable
fun WorkCategoryView(
    modifier: Modifier = Modifier,
    workCategoryList: List<WorkCategory>,
    updateTempWorkCategory: (String) -> Unit,
    navigateFindWorkCategoryToRoutineInUpdateRoutineGraph: () -> Unit,
) {
    Surface(
        color = LiftTheme.colorScheme.no5,
        modifier = modifier.fillMaxSize()
    ) {
        LazyColumn(
            modifier = modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(workCategoryList) { workCategory ->
                Column(
                    horizontalAlignment = Alignment.Start,
                    modifier = modifier
                        .fillMaxWidth()
                        .noRippleClickable
                        {
                            navigateFindWorkCategoryToRoutineInUpdateRoutineGraph()
                            updateTempWorkCategory(workCategory.name)
                        }
                        .background(LiftTheme.colorScheme.no5)
                        .border(
                            width = 1.dp,
                            color = LiftTheme.colorScheme.no8,
                            shape = RoundedCornerShape(size = 12.dp)
                        )
                        .padding(horizontal = 16.dp, vertical = 16.dp),

                    ) {
                    Text(
                        text = workCategory.name,
                        style = LiftTheme.typography.no3,
                        color = LiftTheme.colorScheme.no3
                    )
                }
            }
        }
    }
}


@Composable
@Preview
fun CreateRoutineFindWorkCategoryScreenPreview() {
    LiftMaterialTheme {
        UpdateRoutineFindWorkCategoryScreen(
            searchText = "",
            workPartFilterList = listOf(
                WorkPartFilterSelection("전체", true),
                WorkPartFilterSelection("어깨", false),
                WorkPartFilterSelection("가슴", false),
            ),
            workCategoryList = listOf(
                ModelDataGenerator.WorkCategory.workCategoryModel1.copy(introduce = ""),
                ModelDataGenerator.WorkCategory.workCategoryModel1,
                ModelDataGenerator.WorkCategory.workCategoryModel1,
                ModelDataGenerator.WorkCategory.workCategoryModel1,
                ModelDataGenerator.WorkCategory.workCategoryModel1,
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
