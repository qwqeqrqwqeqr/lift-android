package com.gradation.lift.feature.workReady.findWorkCategory.ui.component

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import com.gradation.lift.designsystem.component.container.LiftDefaultContainer
import com.gradation.lift.designsystem.component.label.PopularWorkCategoryLabel
import com.gradation.lift.designsystem.component.label.RecommendWorkCategoryLabel
import com.gradation.lift.designsystem.component.text.LiftText
import com.gradation.lift.designsystem.component.text.LiftTextStyle
import com.gradation.lift.ui.modifier.noRippleClickable
import com.gradation.lift.designsystem.theme.LiftTheme
import com.gradation.lift.feature.workReady.findWorkCategory.data.model.TagWorkCategory
import com.gradation.lift.feature.workReady.findWorkCategory.data.state.FindWorkCategoryScreenState


@OptIn(ExperimentalFoundationApi::class)
@Composable
internal fun WorkCategoryView(
    modifier: Modifier = Modifier,
    workCategoryList: List<TagWorkCategory>,
    navigateFindWorkCategoryToCreateWorkSetInWorkReadyGraph: (Int) -> Unit,
    findWorkCategoryScreenState: FindWorkCategoryScreenState,
) {
    LaunchedEffect(findWorkCategoryScreenState.lazyListState) {
        snapshotFlow { findWorkCategoryScreenState.lazyListState.firstVisibleItemIndex }
            .collect {
                if (it > 0)
                    findWorkCategoryScreenState.updateSearchSortFilterView(false)
                else
                    findWorkCategoryScreenState.updateSearchSortFilterView(true)
            }
    }
    LazyColumn(
        modifier = modifier
            .background(LiftTheme.colorScheme.no17)
            .fillMaxSize()
            .padding(horizontal = LiftTheme.space.space20),
        state = findWorkCategoryScreenState.lazyListState,
        verticalArrangement = Arrangement.spacedBy(LiftTheme.space.space20)
    ) {
        item { Spacer(modifier = modifier) }
        items(workCategoryList) { workCategory ->
            LiftDefaultContainer(
                modifier = modifier
                    .fillMaxWidth()
                    .animateItemPlacement()
            ) {
                Column(
                    modifier = modifier
                        .fillMaxWidth()
                        .noRippleClickable {
                            navigateFindWorkCategoryToCreateWorkSetInWorkReadyGraph(
                                workCategory.workCategory.id
                            )
                        }
                        .padding(
                            horizontal = LiftTheme.space.space16,
                            vertical = LiftTheme.space.space16
                        ), verticalArrangement = Arrangement.spacedBy(LiftTheme.space.space4)
                ) {
                    Row(
                        modifier = modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        LiftText(
                            text = workCategory.workCategory.name,
                            textStyle = LiftTextStyle.No3,
                            color = LiftTheme.colorScheme.no3,
                            textAlign = TextAlign.Start
                        )
                        Row(
                            modifier = modifier,
                            horizontalArrangement = Arrangement.spacedBy(LiftTheme.space.space8)
                        ) {
                            if (workCategory.popularTag) PopularWorkCategoryLabel()
                            if (workCategory.recommendTag) RecommendWorkCategoryLabel()
                        }
                    }
                }
            }
        }
        item { Spacer(modifier = modifier) }
    }
}


