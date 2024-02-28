package com.gradation.lift.designsystem.component.tab

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.HorizontalDivider
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

@Composable
fun LiftDefaultTab(
    modifier: Modifier = Modifier,
    contentList: List<String>,
    selectedTabIndex: Int,
    onClick: (Int) -> Unit,
) {
    val interactionSource: MutableInteractionSource = remember { MutableInteractionSource() }

    LazyRow(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(LiftTheme.space.space4)
    ) {
        itemsIndexed(contentList) { index, content ->
            val textColor: Color by animateColorAsState(
                targetValue = if (index == selectedTabIndex) LiftTheme.colorScheme.no4 else LiftTheme.colorScheme.no6,
                label = "borderColorAnimation"
            )
            val borderColor: Color by animateColorAsState(
                targetValue = if (index == selectedTabIndex) LiftTheme.colorScheme.no4 else Color.Transparent,
                label = "borderColorAnimation"
            )
            Column(
                modifier = modifier
                    .width(LiftTheme.space.space60)
                    .clickable(
                        interactionSource = interactionSource,
                        indication = null,
                        onClick = { onClick(index) }
                    ),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(LiftTheme.space.space8)
            ) {
                LiftText(
                    textStyle = LiftTextStyle.No3,
                    text = content,
                    color = textColor,
                    textAlign = TextAlign.Center
                )
                HorizontalDivider(
                    color = borderColor,
                    thickness = LiftTheme.space.space2,
                    modifier = modifier.fillMaxWidth()
                )
            }

        }
    }
}
