package com.gradation.lift.designsystem.component.toggleButton

import androidx.compose.foundation.layout.Column
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.Switch
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.SwitchDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.gradation.lift.designsystem.theme.LiftMaterialTheme
import com.gradation.lift.designsystem.theme.LiftTheme

@Composable
fun LiftToggleButton(
    modifier: Modifier = Modifier,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
) {
    Switch(
        modifier = modifier,
        checked = checked,
        onCheckedChange = onCheckedChange,
        colors = SwitchDefaults.colors(
            checkedThumbColor = LiftTheme.colorScheme.no4,
            checkedTrackColor = LiftTheme.colorScheme.no36,
            checkedTrackAlpha = 1f,
            uncheckedThumbColor = LiftTheme.colorScheme.no39,
            uncheckedTrackColor = LiftTheme.colorScheme.no6,
            uncheckedTrackAlpha = 1f,
        )
    )
}

@Composable
@Preview
fun LiftCircleCheckboxPreview(
    modifier: Modifier = Modifier,
) {
    LiftMaterialTheme {
        Column {
            LiftToggleButton(
                modifier = modifier,
                checked = true,
                onCheckedChange = {}
            )
            LiftToggleButton(
                modifier = modifier,
                checked = false,
                onCheckedChange = {}
            )
        }
    }
}