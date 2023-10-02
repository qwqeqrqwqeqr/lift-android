package com.gradation.lift.feature.badge.setting.component.loading

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.gradation.lift.designsystem.brush.SkeletonBrush
import com.gradation.lift.designsystem.theme.LiftTheme


@Composable
fun LoadingBadgeListView(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .background(
                LiftTheme.colorScheme.no5,
                RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp)
            )
            .padding(16.dp)
    ) {
        Text(
            text = "획득한 뱃지",
            color = LiftTheme.colorScheme.no9,
            style = LiftTheme.typography.no3
        )
        LazyVerticalGrid(
            modifier = modifier.padding(16.dp),
            columns = GridCells.Fixed(4),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            items(28) {
                Spacer(
                    modifier = modifier
                        .size(72.dp)
                        .clip(CircleShape)
                        .background(SkeletonBrush())
                )
            }
        }
    }
}