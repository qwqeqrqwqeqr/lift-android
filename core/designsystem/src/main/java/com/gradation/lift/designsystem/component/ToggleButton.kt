package com.gradation.lift.designsystem.component

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.selection.toggleable
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import com.gradation.lift.designsystem.resource.LiftIcon


@Composable
fun ToggleCheckbox(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
) {

    Box(
        modifier = modifier.toggleable(
            value = checked,
            interactionSource= MutableInteractionSource(),
            onValueChange = onCheckedChange,
            role = null,
            indication = null
        ),
        contentAlignment = Alignment.Center
    ) {
        if (checked) {
            Icon(
                modifier = modifier.fillMaxSize(),
                painter = painterResource(LiftIcon.CheckBoxChecked),
                contentDescription = "",
                tint = Color.Unspecified,
            )
        } else {
            Icon(
                modifier = modifier.fillMaxSize(),
                painter = painterResource(LiftIcon.CheckBoxUnChecked),
                contentDescription = "",
                tint = Color.Unspecified,
            )
        }
    }
}


@Composable
fun ToggleVisible(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier.toggleable(
            value = checked,
            interactionSource= MutableInteractionSource(),
            onValueChange = onCheckedChange,
            role = null,
            indication = null
        ),
        contentAlignment = Alignment.Center
    ) {
        if (checked) {
            Icon(
                modifier = modifier.fillMaxSize(),
                painter = painterResource(LiftIcon.EyeSelected),
                contentDescription = "",
                tint = Color.Unspecified,
            )
        } else {
            Icon(
                modifier = modifier.fillMaxSize(),
                painter = painterResource(LiftIcon.EyeUnSelected),
                contentDescription = "",
                tint = Color.Unspecified,
            )
        }
    }
}


