package com.gradation.lift.designsystem.component.label

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.gradation.lift.designsystem.theme.LiftMaterialTheme
import com.gradation.lift.designsystem.theme.LiftTheme


@Composable
fun BadgeNameLabel(
    modifier: Modifier = Modifier,
    badgeName: String,
    textColor: Color,
    backgroundColor: Color,
) {
    Row(
        modifier = modifier
            .background(
                color = backgroundColor,
                shape = RoundedCornerShape(size = LiftTheme.space.space6)
            )
            .padding(
                horizontal = LiftTheme.space.space12,
                vertical = LiftTheme.space.space4
            ),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {

        Text(
            text = badgeName,
            color = textColor,
            style = LiftTheme.typography.no3
        )
    }
}


@Composable
@Preview(showBackground = true)
fun BadgeNameLabelPreview(modifier: Modifier = Modifier) {
    LiftMaterialTheme {
        Column {
            BadgeNameLabel(
                modifier = modifier,
                badgeName = "초심자",
                textColor = LiftTheme.colorScheme.no9,
                backgroundColor = LiftTheme.colorScheme.no58
            )
        }
    }
}

