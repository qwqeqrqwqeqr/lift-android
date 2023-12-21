package com.gradation.lift.designsystem.component.checkBox

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.selection.toggleable
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import com.gradation.lift.designsystem.resource.LiftIcon


@Composable
fun LiftCircleCheckbox(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
) {
    val interactionSource: MutableInteractionSource = remember { MutableInteractionSource() }
    Box(
        modifier = modifier.toggleable(
            value = checked,
            interactionSource = interactionSource,
            onValueChange = onCheckedChange,
            role = null,
            indication = null
        ),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            modifier = modifier.fillMaxSize(),
            painter = painterResource(if (checked) LiftIcon.CheckBoxChecked else LiftIcon.CheckBoxUnChecked),
            contentDescription = "",
            tint = Color.Unspecified,
        )
    }
}
