package com.gradation.lift.designsystem.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Icon
import androidx.compose.material3.IconToggleButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.gradation.lift.designsystem.resource.LiftIcon
import com.gradation.lift.designsystem.theme.LiftTheme


@Composable
fun ToggleCheckbox(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
) {
    IconToggleButton(
        checked = checked,
        onCheckedChange = onCheckedChange,
        modifier = modifier
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
fun ToggleEye(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
) {
    IconToggleButton(
        checked = checked,
        onCheckedChange = onCheckedChange,
        modifier = modifier
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


@Preview
@Composable
fun ToggleButtonPreview() {
    LiftTheme {
        Column {
            ToggleCheckbox(true, {})
            ToggleEye(true, {})
        }
    }
}