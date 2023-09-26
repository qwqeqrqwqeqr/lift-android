package com.gradation.lift.feature.badge.badge.component.fail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.gradation.lift.designsystem.theme.LiftTheme


@Composable
fun FailBadgeStoreView(
    modifier: Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(
                LiftTheme.colorScheme.no5,
                RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp)
            )
    ) {
        Column(modifier = modifier.padding(16.dp)) {
            Text(
                text = "뱃지보관함",
                color = LiftTheme.colorScheme.no9,
                style = LiftTheme.typography.no3
            )
        }
        FailBadgeFilterView(modifier)
        FailBadgeListView(modifier)

    }
}


