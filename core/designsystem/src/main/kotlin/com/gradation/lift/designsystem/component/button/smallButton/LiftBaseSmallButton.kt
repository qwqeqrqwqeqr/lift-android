package com.gradation.lift.designsystem.component.button.smallButton

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.gradation.lift.designsystem.theme.LiftTheme

@Composable
fun LiftBaseSmallButton(
    modifier: Modifier = Modifier,
    content: @Composable RowScope.() -> Unit,
) {
    Row(
        modifier = modifier
            .height(LiftTheme.space.space28)
            .background(
                LiftTheme.colorScheme.no1,
                RoundedCornerShape(LiftTheme.space.space6)
            )
            .padding(horizontal = LiftTheme.space.space6),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start,
        content = content
    )
}