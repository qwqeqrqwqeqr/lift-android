package com.gradation.lift.designsystem.component.tab

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import com.gradation.lift.designsystem.component.text.LiftText
import com.gradation.lift.designsystem.component.text.LiftTextStyle
import com.gradation.lift.designsystem.theme.LiftTheme

/**
 * [LiftDoubleTab]
 * @param valueList 탭의 이름 목록
 * @param selectedIndex 현재 선택된 인덱스
 * @since 2024-01-28 15:19:13
 */
@Composable
fun LiftDoubleTab(
    modifier: Modifier = Modifier,
    valueList: List<String>,
    selectedIndex: Int,
    updateSelected: (Int) -> Unit,
) {
    val interactionSource = remember { MutableInteractionSource() }
    Row(
        modifier = modifier
            .height(LiftTheme.space.space44)
            .background(LiftTheme.colorScheme.no1, RoundedCornerShape(LiftTheme.space.space12)),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        valueList.forEachIndexed { index, value ->
            val textColor: Color by animateColorAsState(
                if (selectedIndex == index) LiftTheme.colorScheme.no3 else LiftTheme.colorScheme.no10,
                label = "textColorAnimation"
            )
            val indicatorColor: Color by animateColorAsState(
                targetValue = if (selectedIndex == index) LiftTheme.colorScheme.no5 else LiftTheme.colorScheme.no1,
                label = "indicatorColorAnimation",
            )


            Box(
                modifier = modifier
                    .padding(LiftTheme.space.space4)
                    .height(LiftTheme.space.space36)
                    .weight(1f)
                    .clickable(
                        onClick = { updateSelected(index) },
                        interactionSource = interactionSource,
                        indication = null
                    )
                    .background(
                        indicatorColor,
                        RoundedCornerShape(LiftTheme.space.space8)
                    ),
                contentAlignment = Alignment.Center
            ) {
                LiftText(
                    modifier = modifier,
                    text = value,
                    textAlign = TextAlign.Center,
                    textStyle = LiftTextStyle.No3,
                    color = textColor
                )

            }

        }
    }
}



