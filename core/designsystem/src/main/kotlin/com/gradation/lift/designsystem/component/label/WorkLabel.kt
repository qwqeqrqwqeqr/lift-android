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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.gradation.lift.designsystem.theme.LiftMaterialTheme
import com.gradation.lift.designsystem.theme.LiftTheme


@Composable
fun WorkCompleteLabel(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .background(
                color = LiftTheme.colorScheme.popularWorkCategoryLabelBackgroundColor,
                shape = RoundedCornerShape(size = LiftTheme.space.space32)
            )
            .padding(
                horizontal = LiftTheme.space.space8,
                vertical = LiftTheme.space.space4
            ),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {

        Text(
            text = "완료",
            color = LiftTheme.colorScheme.popularWorkCategoryLabelColor,
            style = LiftTheme.typography.no7.copy(fontWeight = FontWeight.Bold)
        )
    }
}


@Composable
@Preview(showBackground = true)
fun WorkLabelPreview() {
    LiftMaterialTheme {
        Column {
            WorkCompleteLabel()
        }
    }
}

