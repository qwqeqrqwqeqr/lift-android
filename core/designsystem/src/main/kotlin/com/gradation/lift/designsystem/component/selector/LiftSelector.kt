package com.gradation.lift.designsystem.component.selector

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.selection.toggleable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import com.gradation.lift.designsystem.component.button.LiftButtonText
import com.gradation.lift.designsystem.theme.LiftTheme


@Composable
fun LiftPrimarySelector(
    modifier: Modifier = Modifier,
    text: String,
    isSelected: Boolean = true,
    onClick: () -> Unit,
) {
    val interactionSource: MutableInteractionSource = remember { MutableInteractionSource() }

    val backgroundColor: Color by animateColorAsState(
        if (isSelected) LiftTheme.colorScheme.no5
        else LiftTheme.colorScheme.no1,
        label = "contentColor"
    )
    val textColor: Color by animateColorAsState(
        if (isSelected) LiftTheme.colorScheme.no4
        else LiftTheme.colorScheme.no2,
        label = "textColor"
    )
    val borderColor: Color by animateColorAsState(
        if (isSelected) LiftTheme.colorScheme.no4
        else Color.Transparent,
        label = "borderColor"
    )
    Row(
        modifier = modifier
            .height(LiftTheme.space.space48)
            .background(backgroundColor, RoundedCornerShape(size = LiftTheme.space.space12))
            .border(
                width = LiftTheme.space.space2,
                color = borderColor,
                shape = RoundedCornerShape(size = LiftTheme.space.space12)
            )
            .clickable(
                interactionSource = interactionSource,
                indication = null,
                onClick= onClick
            ),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        LiftButtonText(
            modifier = modifier,
            text = text,
            color = textColor,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun LiftIconSelector(
    modifier: Modifier = Modifier,
    icon: @Composable () -> Unit,
    isSelected: Boolean = true,
    onSelectedChange: (Boolean) -> Unit,
) {
    val interactionSource: MutableInteractionSource = remember { MutableInteractionSource() }

    val backgroundColor: Color by animateColorAsState(
        if (isSelected) LiftTheme.colorScheme.no4
        else LiftTheme.colorScheme.no1,
        label = "contentColor"
    )

    Row(
        modifier = modifier
            .height(LiftTheme.space.space48)
            .background(backgroundColor, RoundedCornerShape(size = LiftTheme.space.space12))
            .toggleable(
                value = isSelected,
                onValueChange = onSelectedChange,
                interactionSource = interactionSource,
                indication = null,
            ),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        content = { icon() }
    )
}