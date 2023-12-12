package com.gradation.lift.designsystem.component.label

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.gradation.lift.designsystem.resource.LiftIcon
import com.gradation.lift.designsystem.theme.LiftMaterialTheme
import com.gradation.lift.designsystem.theme.LiftTheme


@Composable
fun RecommendWorkCategoryLabel(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .width(LiftTheme.space.space48)
            .background(
                color = LiftTheme.colorScheme.recommendWorkCategoryLabelBackgroundColor,
                shape = RoundedCornerShape(size = LiftTheme.space.space32)
            )
            .padding(
                horizontal = LiftTheme.space.space8,
                vertical = LiftTheme.space.space4
            ),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(LiftIcon.Thumb),
            contentDescription = "",
            tint = LiftTheme.colorScheme.recommendWorkCategoryLabelColor,
        )
        Text(
            text = "추천",
            color = LiftTheme.colorScheme.recommendWorkCategoryLabelColor,
            style = LiftTheme.typography.no7.copy(fontWeight = FontWeight.Bold)
        )
    }
}


@Composable
fun PopularWorkCategoryLabel(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .width(LiftTheme.space.space48)
            .background(
                color = LiftTheme.colorScheme.popularWorkCategoryLabelBackgroundColor,
                shape = RoundedCornerShape(size = LiftTheme.space.space32)
            )
            .padding(
                horizontal = LiftTheme.space.space8,
                vertical = LiftTheme.space.space4
            ),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(LiftIcon.Fire),
            contentDescription = "",
            tint = LiftTheme.colorScheme.popularWorkCategoryLabelColor,
        )
        Text(
            text = "인기",
            color = LiftTheme.colorScheme.popularWorkCategoryLabelColor,
            style = LiftTheme.typography.no7.copy(fontWeight = FontWeight.Bold)
        )
    }
}


@Composable
@Preview(showBackground = true)
fun WorkCategoryLabelPreview() {
    LiftMaterialTheme {
        Column {
            RecommendWorkCategoryLabel()
            PopularWorkCategoryLabel()
        }
    }
}

