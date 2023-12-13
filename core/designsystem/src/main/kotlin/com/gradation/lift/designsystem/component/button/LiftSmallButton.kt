package com.gradation.lift.designsystem.component.button

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import com.gradation.lift.designsystem.resource.LiftIcon
import com.gradation.lift.designsystem.theme.LiftTheme

@Composable
fun LiftSmallButton(
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit,
) {
    val interactionSource: MutableInteractionSource = remember { MutableInteractionSource() }
    val isPressed: Boolean by interactionSource.collectIsPressedAsState()

    val backgroundColor: Color by animateColorAsState(
        if (isPressed) LiftTheme.colorScheme.no39
        else LiftTheme.colorScheme.no1,
        label = "contentColor"
    )

    val textColor: Color by animateColorAsState(
        if (isPressed) LiftTheme.colorScheme.no36
        else LiftTheme.colorScheme.no4,
        label = "textColor"
    )


    Row(
        modifier = modifier
            .height(LiftTheme.space.space28)
            .background(backgroundColor, RoundedCornerShape(size = LiftTheme.space.space6))
            .padding(
                horizontal = LiftTheme.space.space6,
                vertical = LiftTheme.space.space5
            )
            .clickable(
                interactionSource = interactionSource,
                indication = null,
                onClick = onClick,
            ),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(
            LiftTheme.space.space4,
            Alignment.CenterHorizontally
        )
    ) {
        Icon(
            modifier = modifier.size(LiftTheme.space.space16),
            painter = painterResource(id = LiftIcon.Plus),
            contentDescription = "Plus",
            tint = LiftTheme.colorScheme.no2
        )
        LiftSmallButtonText(
            modifier = modifier,
            text = text,
            color = textColor,
            textAlign = TextAlign.Center
        )
    }
}