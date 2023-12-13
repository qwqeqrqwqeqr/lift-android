package com.gradation.lift.designsystem.component.button

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.gradation.lift.designsystem.theme.LiftTheme

@Composable
fun LiftSolidButton(
    modifier: Modifier = Modifier,
    text: String,
    enabled: Boolean = true,
    onClick: () -> Unit,
) {
    val interactionSource: MutableInteractionSource = remember { MutableInteractionSource() }
    val isPressed: Boolean by interactionSource.collectIsPressedAsState()

    val backgroundColor: Color by animateColorAsState(
        if (!enabled) LiftTheme.colorScheme.no26
        else if (isPressed) LiftTheme.colorScheme.no36
        else LiftTheme.colorScheme.no4,
        label = "contentColor"
    )

    val textColor: Color by animateColorAsState(
        if (!enabled) LiftTheme.colorScheme.no5
        else if (isPressed) LiftTheme.colorScheme.no5
        else LiftTheme.colorScheme.no5,
        label = "textColor"
    )


    Row(
        modifier = modifier
            .height(LiftTheme.space.space48)
            .fillMaxWidth()
            .background(backgroundColor, RoundedCornerShape(size = LiftTheme.space.space12))
            .clickable(
                interactionSource = interactionSource,
                indication = null,
                onClick = onClick,
                enabled = enabled
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
fun LiftDefaultButton(
    modifier: Modifier = Modifier,
    text: String,
    enabled: Boolean = true,
    onClick: () -> Unit,
) {
    val interactionSource: MutableInteractionSource = remember { MutableInteractionSource() }
    val isPressed: Boolean by interactionSource.collectIsPressedAsState()

    val backgroundColor: Color by animateColorAsState(
        if (!enabled) LiftTheme.colorScheme.no6
        else if (isPressed) LiftTheme.colorScheme.no39
        else LiftTheme.colorScheme.no1,
        label = "contentColor"
    )
    val textColor: Color by animateColorAsState(
        if (!enabled) LiftTheme.colorScheme.no2
        else if (isPressed) LiftTheme.colorScheme.no2
        else LiftTheme.colorScheme.no2,
        label = "textColor"
    )


    Row(
        modifier = modifier
            .height(LiftTheme.space.space48)
            .fillMaxWidth()
            .background(backgroundColor, RoundedCornerShape(size = LiftTheme.space.space12))
            .clickable(
                interactionSource = interactionSource,
                indication = null,
                onClick = onClick,
                enabled = enabled
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
fun LiftErrorButton(
    modifier: Modifier = Modifier,
    text: String,
    enabled: Boolean = true,
    onClick: () -> Unit,
) {
    val interactionSource: MutableInteractionSource = remember { MutableInteractionSource() }
    val isPressed: Boolean by interactionSource.collectIsPressedAsState()

    val backgroundColor: Color by animateColorAsState(
        if (!enabled) LiftTheme.colorScheme.no37
        else if (isPressed) LiftTheme.colorScheme.no38
        else LiftTheme.colorScheme.no12,
        label = "contentColor"
    )
    val textColor: Color by animateColorAsState(
        if (!enabled) LiftTheme.colorScheme.no5
        else if (isPressed) LiftTheme.colorScheme.no5
        else LiftTheme.colorScheme.no5,
        label = "textColor"
    )


    Row(
        modifier = modifier
            .height(LiftTheme.space.space48)
            .fillMaxWidth()
            .background(backgroundColor, RoundedCornerShape(size = LiftTheme.space.space12))
            .clickable(
                interactionSource = interactionSource,
                indication = null,
                onClick = onClick,
                enabled = enabled
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
fun LiftPrimaryButton(
    modifier: Modifier = Modifier,
    text: String,
    enabled: Boolean = true,
    onClick: () -> Unit,
) {
    val interactionSource: MutableInteractionSource = remember { MutableInteractionSource() }
    val isPressed: Boolean by interactionSource.collectIsPressedAsState()

    val backgroundColor: Color by animateColorAsState(
        if (!enabled) LiftTheme.colorScheme.no1
        else if (isPressed) LiftTheme.colorScheme.no39
        else LiftTheme.colorScheme.no5,
        label = "contentColor"
    )
    val textColor: Color by animateColorAsState(
        if (!enabled) LiftTheme.colorScheme.no10
        else if (isPressed) LiftTheme.colorScheme.no4
        else LiftTheme.colorScheme.no4,
        label = "textColor"
    )
    val borderColor: Color by animateColorAsState(
        if (!enabled) LiftTheme.colorScheme.no10
        else LiftTheme.colorScheme.no4,
        label = "borderColor"
    )




    Row(
        modifier = modifier
            .height(LiftTheme.space.space48)
            .fillMaxWidth()
            .background(backgroundColor, RoundedCornerShape(size = LiftTheme.space.space12))
            .border(
                width = LiftTheme.space.space2,
                color = borderColor,
                shape = RoundedCornerShape(size = LiftTheme.space.space12)
            )
            .clickable(
                interactionSource = interactionSource,
                indication = null,
                onClick = onClick,
                enabled = enabled
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


@Preview
@Composable
fun LiftButtonPreview() {

    Column(verticalArrangement = Arrangement.spacedBy(LiftTheme.space.space8)) {
        LiftSolidButton(text = "버튼", enabled = true, onClick = {})
        LiftSolidButton(text = "버튼", enabled = false, onClick = {})
        LiftDefaultButton(text = "버튼", enabled = true, onClick = {})
        LiftDefaultButton(text = "버튼", enabled = false, onClick = {})
        LiftPrimaryButton(text = "버튼", enabled = true, onClick = {})
        LiftPrimaryButton(text = "버튼", enabled = false, onClick = {})
        LiftErrorButton(text = "버튼", enabled = true, onClick = {})
        LiftErrorButton(text = "버튼", enabled = false, onClick = {})
    }


}