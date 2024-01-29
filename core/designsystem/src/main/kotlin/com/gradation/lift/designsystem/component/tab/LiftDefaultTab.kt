package com.gradation.lift.designsystem.component.tab

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import com.gradation.lift.designsystem.component.text.LiftText
import com.gradation.lift.designsystem.component.text.LiftTextStyle
import com.gradation.lift.designsystem.theme.LiftTheme

@Composable
fun LiftDefaultTab(
    modifier: Modifier = Modifier,
    contentList: List<String>,
    selectedTabIndex: Int,
    onClick: (Int) -> Unit,
) {
    TabRow(
        modifier = modifier.padding(horizontal = LiftTheme.space.space20),
        selectedTabIndex = selectedTabIndex,
        containerColor = LiftTheme.colorScheme.no5,
        indicator = { tabPositions ->
            TabRowDefaults.SecondaryIndicator(
                modifier = modifier.tabIndicatorOffset(tabPositions[selectedTabIndex]),
                color = LiftTheme.colorScheme.no4,
                height = LiftTheme.space.space2
            )
        },
        divider = {},
    ) {
        contentList.forEachIndexed { index, content ->
            Tab(
                text = {
                    LiftText(
                        textStyle = LiftTextStyle.No3,
                        text = content,
                        color = Color.Unspecified,
                        textAlign = TextAlign.Center
                    )
                },
                selected = selectedTabIndex == index,
                unselectedContentColor = LiftTheme.colorScheme.no6,
                selectedContentColor = LiftTheme.colorScheme.no4,
                onClick = { onClick(index) },
            )
        }
    }
}