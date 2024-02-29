package com.gradation.lift.designsystem.component.chip

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
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

@Composable
fun LiftDefaultChip(
    modifier: Modifier = Modifier,
    text: String,
    isSelected: Boolean = true,
    enabled: Boolean = true,
    onClick: (() -> Unit)? = null,
) {
    val interactionSource: MutableInteractionSource = remember { MutableInteractionSource() }

    val backgroundColor: Color by animateColorAsState(
        if (!enabled && isSelected) LiftTheme.colorScheme.no13
        else if (enabled && isSelected) LiftTheme.colorScheme.no4
        else LiftTheme.colorScheme.no1,
        label = "contentColor"
    )
    val textColor: Color by animateColorAsState(
        if (isSelected) LiftTheme.colorScheme.no5
        else LiftTheme.colorScheme.no9,
        label = "textColor"
    )

    Row(
        modifier = modifier
            .background(backgroundColor, RoundedCornerShape(size = LiftTheme.space.space6))
            .height(LiftTheme.space.space36)
            .padding(horizontal = LiftTheme.space.space12)
            .clickable(
                onClick = onClick ?: {},
                enabled = enabled,
                interactionSource = interactionSource,
                indication = null,
            ),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
    ){
        LiftText(
            modifier = modifier,
            text = text,
            color = textColor,
            textAlign = TextAlign.Center,
            textStyle = if(isSelected) LiftTextStyle.No3 else  LiftTextStyle.No4
        )
    }
}

