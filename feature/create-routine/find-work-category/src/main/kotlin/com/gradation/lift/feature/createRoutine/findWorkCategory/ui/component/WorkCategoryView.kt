package com.gradation.lift.feature.createRoutine.findWorkCategory.ui.component

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import com.gradation.lift.designsystem.component.container.LiftDefaultContainer
import com.gradation.lift.designsystem.component.label.PopularWorkCategoryLabel
import com.gradation.lift.designsystem.component.label.RecommendWorkCategoryLabel
import com.gradation.lift.designsystem.component.label.WorkPartLabel
import com.gradation.lift.designsystem.component.text.LiftText
import com.gradation.lift.designsystem.component.text.LiftTextStyle
import com.gradation.lift.designsystem.theme.LiftTheme
import com.gradation.lift.feature.createRoutine.findWorkCategory.data.model.TagWorkCategory
import com.gradation.lift.feature.createRoutine.findWorkCategory.data.state.FindWorkCategoryScreenState
import com.gradation.lift.ui.modifier.noRippleClickable


@OptIn(ExperimentalFoundationApi::class)
@Composable
internal fun WorkCategoryView(
    modifier: Modifier = Modifier,
    workCategoryList: List<TagWorkCategory>,
    navigateFindWorkCategoryToCreateWorkSetInCreateRoutineGraph: (Int) -> Unit,
    findWorkCategoryScreenState: FindWorkCategoryScreenState,
) {
    LazyColumn(
        modifier = modifier
            .background(LiftTheme.colorScheme.no17)
            .fillMaxSize()
            .padding(
                end = LiftTheme.space.space20,
                start = LiftTheme.space.space20
            ),
        state = findWorkCategoryScreenState.lazyListState,
        verticalArrangement = Arrangement.spacedBy(LiftTheme.space.space20)
    ) {
        item { Spacer(modifier = modifier) }
        items(
            items = workCategoryList,
            key = { it.workCategory.id }
        ) { workCategory ->
            LiftDefaultContainer(
                modifier = modifier
                    .fillMaxWidth()
                    .animateItemPlacement()
            ) {
                Column(
                    modifier = modifier
                        .fillMaxWidth()
                        .noRippleClickable {
                            navigateFindWorkCategoryToCreateWorkSetInCreateRoutineGraph(
                                workCategory.workCategory.id
                            )
                        }
                        .padding(
                            horizontal = LiftTheme.space.space16,
                            vertical = LiftTheme.space.space16
                        ),
                    verticalArrangement = Arrangement.spacedBy(LiftTheme.space.space12)
                ) {
                    if (workCategory.popularTag || workCategory.recommendTag) {
                        Row(
                            modifier = modifier,
                            horizontalArrangement = Arrangement.spacedBy(LiftTheme.space.space8)
                        ) {
                            if (workCategory.popularTag) PopularWorkCategoryLabel()
                            if (workCategory.recommendTag) RecommendWorkCategoryLabel()
                        }
                    }
                    Column(
                        modifier = modifier.fillMaxWidth(),
                        verticalArrangement = Arrangement.spacedBy(LiftTheme.space.space4)
                    ) {
                        Row(
                            modifier = modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(LiftTheme.space.space8),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            workCategory.workCategory.workPart.forEach {
                                WorkPartLabel(workPart = it)
                            }
                            LiftText(
                                text = workCategory.workCategory.name,
                                textStyle = LiftTextStyle.No2,
                                color = LiftTheme.colorScheme.no3,
                                textAlign = TextAlign.Start
                            )
                        }
                        workCategory.workCategory.introduce?.let {
                            if (it.isNotEmpty())
                                LiftText(
                                    text = it,
                                    textStyle = LiftTextStyle.No4,
                                    color = LiftTheme.colorScheme.no9,
                                    textAlign = TextAlign.Start
                                )
                        }
                    }
                }
            }
        }
        item { Spacer(modifier = modifier) }
    }
}



