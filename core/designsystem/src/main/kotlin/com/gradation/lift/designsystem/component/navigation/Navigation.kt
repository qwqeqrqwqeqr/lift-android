package com.gradation.lift.designsystem.component.navigation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import com.gradation.lift.designsystem.component.text.LiftText
import com.gradation.lift.designsystem.component.text.LiftTextStyle
import com.gradation.lift.designsystem.theme.LiftTheme


@Composable
fun LiftNavigationItem(
    modifier: Modifier = Modifier,
    interactionSource: MutableInteractionSource,
    onClick:()-> Unit,
    icon:Int,
    text:String,
    color: Color
) {
    Column(
        modifier = modifier
            .clickable(
                interactionSource = interactionSource,
                indication = null,
                onClick = onClick
            ),
        verticalArrangement = Arrangement.spacedBy(
            LiftTheme.space.space4,
            Alignment.CenterVertically
        ),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Icon(
            painter = painterResource(id = icon),
            contentDescription = "navigationIcon",
            tint = color
        )
        LiftText(
            textStyle = LiftTextStyle.No8,
            text = text,
            color = color,
            textAlign = TextAlign.Center
        )
    }
}




