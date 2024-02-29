package com.gradation.lift.designsystem.component.checkBox

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.selection.toggleable
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.gradation.lift.designsystem.resource.LiftIcon
import com.gradation.lift.designsystem.theme.LiftMaterialTheme
import com.gradation.lift.designsystem.theme.LiftTheme

enum class LiftCircleCheckBoxSize {
    Size24,
    Size28
}

@Composable
fun LiftCircleCheckbox(
    modifier: Modifier = Modifier,
    checked: Boolean,
    liftCircleCheckBoxSize: LiftCircleCheckBoxSize,
    onCheckedChange: (Boolean) -> Unit,
) {
    val interactionSource: MutableInteractionSource = remember { MutableInteractionSource() }
    Box(
        modifier = modifier
            .size(
                when (liftCircleCheckBoxSize) {
                    LiftCircleCheckBoxSize.Size24 -> LiftTheme.space.space24
                    LiftCircleCheckBoxSize.Size28 -> LiftTheme.space.space28
                }
            )
            .toggleable(
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


@Composable
@Preview
fun LiftCircleCheckboxPreview(
    modifier: Modifier = Modifier,
) {
    LiftMaterialTheme {
        Column {
            LiftCircleCheckbox(
                modifier = modifier,
                checked = true,
                liftCircleCheckBoxSize = LiftCircleCheckBoxSize.Size24,
                onCheckedChange = {}
            )
            LiftCircleCheckbox(
                modifier = modifier,
                checked = false,
                liftCircleCheckBoxSize = LiftCircleCheckBoxSize.Size24,
                onCheckedChange = {}
            )
            LiftCircleCheckbox(
                modifier = modifier,
                checked = true,
                liftCircleCheckBoxSize = LiftCircleCheckBoxSize.Size28,
                onCheckedChange = {}
            )
            LiftCircleCheckbox(
                modifier = modifier,
                checked = false,
                liftCircleCheckBoxSize = LiftCircleCheckBoxSize.Size28,
                onCheckedChange = {}
            )
        }
    }
}