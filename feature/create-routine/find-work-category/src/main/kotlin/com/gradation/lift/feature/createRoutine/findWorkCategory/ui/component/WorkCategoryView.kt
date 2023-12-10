package com.gradation.lift.feature.createRoutine.findWorkCategory.ui.component

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import com.gradation.lift.designsystem.component.label.PopularWorkCategoryLabel
import com.gradation.lift.designsystem.component.label.RecommendWorkCategoryLabel
import com.gradation.lift.ui.modifier.noRippleClickable
import com.gradation.lift.designsystem.theme.LiftTheme
import com.gradation.lift.feature.createRoutine.findWorkCategory.data.model.TagWorkCategory


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun WorkCategoryView(
    modifier: Modifier = Modifier,
    workCategoryList: List<TagWorkCategory>,
    navigateFindWorkCategoryToRoutineInCreateRoutineGraph: (Int) -> Unit,
) {
    LazyColumn(
        modifier = modifier
            .background(LiftTheme.colorScheme.no17)
            .fillMaxSize()
            .padding(LiftTheme.space.paddingSpace),
        verticalArrangement = Arrangement.spacedBy(LiftTheme.space.space8)
    ) {
        items(workCategoryList) { workCategory ->
            Column(
                horizontalAlignment = Alignment.Start,
                modifier = modifier

                    .fillMaxWidth()
                    .noRippleClickable {
                        navigateFindWorkCategoryToRoutineInCreateRoutineGraph(workCategory.workCategory.id)
                    }
                    .border(
                        width = LiftTheme.space.space1,
                        color = LiftTheme.colorScheme.no8,
                        shape = RoundedCornerShape(LiftTheme.space.space12)
                    )
                    .background(
                        LiftTheme.colorScheme.no5,
                        shape = RoundedCornerShape(LiftTheme.space.space12)
                    )
                    .clip(shape = RoundedCornerShape(LiftTheme.space.space12))
                    .padding(LiftTheme.space.paddingSpace)
                    .animateItemPlacement(),

                ) {
                Row(modifier=modifier,
                    horizontalArrangement = Arrangement.spacedBy(LiftTheme.space.space6)) {
                    Text(
                        text = workCategory.workCategory.name,
                        style = LiftTheme.typography.no3,
                        color = LiftTheme.colorScheme.no3
                    )
                    if(workCategory.popularTag) PopularWorkCategoryLabel()
                    if(workCategory.recommendTag) RecommendWorkCategoryLabel()
                }
            }
        }
    }
}


