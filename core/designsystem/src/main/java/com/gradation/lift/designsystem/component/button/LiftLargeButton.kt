package com.gradation.lift.designsystem.component.button

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.gradation.lift.designsystem.theme.LiftTheme

@Composable
fun LiftSolidLargeButton(
    modifier: Modifier = Modifier,
    text: String,
    enabled: Boolean = true,
    onClick: () -> Unit,
) {
    Button(
        onClick = onClick,
        modifier = modifier
            .height(LiftTheme.space.space48)
            .fillMaxWidth(),
        enabled = enabled,
        shape = RoundedCornerShape(size = LiftTheme.space.space12),
        colors = ButtonDefaults.buttonColors(
            containerColor = LiftTheme.colorScheme.no4,
            contentColor = LiftTheme.colorScheme.no5,
        ),
        contentPadding = PaddingValues(LiftTheme.space.space10),

        ) {
        LiftButtonText(modifier, text)
    }
}

@Composable
fun LiftDefaultLargeButton(
    modifier: Modifier = Modifier,
    text: String,
    enabled: Boolean = true,
    onClick: () -> Unit,
) {
    Button(
        onClick = onClick,
        modifier = modifier
            .height(LiftTheme.space.space48)
            .fillMaxWidth(),
        enabled = enabled,
        shape = RoundedCornerShape(size = LiftTheme.space.space12),
        colors = ButtonDefaults.buttonColors(
            containerColor = LiftTheme.colorScheme.no13,
            contentColor = LiftTheme.colorScheme.no5,
        ),
        contentPadding = PaddingValues(LiftTheme.space.space10),

        ) {
        LiftButtonText(modifier, text)
    }
}

@Composable
fun LiftPrimaryLargeButton(
    modifier: Modifier = Modifier,
    text: String,
    enabled: Boolean = true,
    onClick: () -> Unit,
) {
    Button(
        onClick = onClick,
        modifier = modifier
            .height(LiftTheme.space.space48)
            .border(
                width = LiftTheme.space.space2,
                color = LiftTheme.colorScheme.no4,
                shape = RoundedCornerShape(size = LiftTheme.space.space12)
            )
            .fillMaxWidth(),
        enabled = enabled,
        shape = RoundedCornerShape(size = LiftTheme.space.space12),
        colors = ButtonDefaults.buttonColors(
            containerColor = LiftTheme.colorScheme.no5,
            contentColor = LiftTheme.colorScheme.no4,
        ),
        contentPadding = PaddingValues(LiftTheme.space.space10),

        ) {
        LiftButtonText(modifier, text)
    }
}


@Preview
@Composable
fun LiftLargeButtonPreview() {

    Column(verticalArrangement = Arrangement.spacedBy(LiftTheme.space.space8)) {
        LiftSolidLargeButton(text = "버튼", enabled = true, onClick = {})
        LiftDefaultLargeButton(text = "버튼", enabled = true, onClick = {})
        LiftPrimaryLargeButton(text = "버튼", enabled = true, onClick = {})
    }

}