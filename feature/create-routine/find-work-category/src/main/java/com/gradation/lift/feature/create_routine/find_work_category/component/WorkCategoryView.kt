package com.gradation.lift.feature.create_routine.find_work_category.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.gradation.lift.designsystem.theme.LiftTheme
import com.gradation.lift.model.work.WorkCategory


@Composable
fun WorkCategoryView(
    modifier: Modifier = Modifier,
    workCategoryList: State<List<WorkCategory>>,
    onClickWorkCategory: (String) -> Unit,
) {
    Surface(
        color = LiftTheme.colorScheme.no5,
        modifier = modifier.fillMaxWidth()
    ) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
            ) {
                items(workCategoryList.value) { workCategory ->
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Box(
                            modifier = modifier
                                .background(
                                    color = LiftTheme.colorScheme.no1,
                                    shape = RoundedCornerShape(size = 12.dp)
                                )
                                .align(alignment = Alignment.CenterHorizontally)
                                .size(156.dp)
                                .clickable(
                                    onClick = { onClickWorkCategory(workCategory.name) }
                                )
                        )
                        Spacer(modifier = modifier.padding(2.dp))
                        Text(
                            text = workCategory.name,
                            style = LiftTheme.typography.no3,
                            color = LiftTheme.colorScheme.no3,
                            textAlign = TextAlign.Center
                        )
                    }

                }
            }

        }
    }
}