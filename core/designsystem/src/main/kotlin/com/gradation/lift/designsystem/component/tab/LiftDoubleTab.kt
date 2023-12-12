package com.gradation.lift.designsystem.component.tab

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.PrimaryTabRow
import androidx.compose.material3.Tab
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import com.gradation.lift.designsystem.component.text.LiftText
import com.gradation.lift.designsystem.component.text.LiftTextStyle
import com.gradation.lift.designsystem.theme.LiftTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

/**
 * [LiftDoubleTab]
 * @param tabNameList 탭의 이름 목록
 * @since 2023-12-07 10:31:26
 */
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun LiftDoubleTab(
    modifier: Modifier = Modifier,
    tabNameList: List<String>,
    coroutineScope: CoroutineScope,
    pagerState: PagerState
) {
    PrimaryTabRow(
        modifier = modifier
            .height(LiftTheme.space.space44)
            .clip(RoundedCornerShape(LiftTheme.space.space12)),
        containerColor = LiftTheme.colorScheme.no1,
        selectedTabIndex = pagerState.currentPage,
        indicator = {},
        divider = {},
    ) {
        tabNameList.forEachIndexed { index, item ->
            Tab(
                modifier = modifier
                    .padding(LiftTheme.space.space4)
                    .height(LiftTheme.space.space36)
                    .background(
                        if (pagerState.currentPage == index) LiftTheme.colorScheme.no5 else LiftTheme.colorScheme.no1,
                        RoundedCornerShape(LiftTheme.space.space8)
                    ),
                selected = pagerState.currentPage == index,
                onClick = {
                    coroutineScope.launch {
                        pagerState.animateScrollToPage(index)
                    }
                },
                text = {
                    if (pagerState.currentPage == index)
                        LiftText(
                            text = item,
                            textAlign = TextAlign.Center,
                            textStyle = LiftTextStyle.No3,
                            color = LiftTheme.colorScheme.no9
                        )
                    else
                        LiftText(
                            text = item,
                            textAlign = TextAlign.Center,
                            textStyle = LiftTextStyle.No3,
                            color = LiftTheme.colorScheme.no8
                        )
                }
            )
        }
    }
}