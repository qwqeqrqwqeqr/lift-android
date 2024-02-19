package com.gradation.lift.designsystem.component.radio

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.tooling.preview.Preview
import com.gradation.lift.designsystem.component.icon.IconBoxSize
import com.gradation.lift.designsystem.component.icon.IconType
import com.gradation.lift.designsystem.component.icon.LiftIconBox
import com.gradation.lift.designsystem.resource.LiftIcon
import com.gradation.lift.designsystem.theme.LiftMaterialTheme

@Composable
fun LiftRadioButton(
    modifier: Modifier = Modifier,
    selected: Boolean,
    onClick: (() -> Unit),
    enabled: Boolean = true,
) {
    val interactionSource: MutableInteractionSource = remember { MutableInteractionSource() }
    LiftIconBox(
        modifier = modifier.clickable(
            interactionSource = interactionSource,
            indication = null,
            onClick = onClick,
            enabled = enabled,
            role = Role.RadioButton
        ),
        icon = if (selected) LiftIcon.RadioChecked else LiftIcon.RadioUnchecked,
        iconType = IconType.Painter,
        iconBoxSize = IconBoxSize.Size20
    )
}

@Composable
@Preview
fun LiftRadioButtonPreview(
    modifier: Modifier = Modifier,
) {
    LiftMaterialTheme {
        Column {
            LiftRadioButton(
                modifier,
                true,
                {}
            )
            LiftRadioButton(
                modifier,
                false,
                {}
            )
        }
    }
}