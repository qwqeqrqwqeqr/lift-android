package com.gradation.lift.feature.badge.badge.component.loading

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import com.gradation.lift.designsystem.brush.SkeletonBrush
import com.gradation.lift.designsystem.theme.LiftTheme


@Composable
fun LoadingBadgeInfoView(
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(
                LiftTheme.colorScheme.no5,
                RoundedCornerShape(bottomStart = 24.dp, bottomEnd = 24.dp)
            )
            .padding(top = 16.dp, start = 16.dp, end = 16.dp, bottom = 24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = buildAnnotatedString {
                withStyle(
                    style = SpanStyle(fontWeight = FontWeight(800)),
                ) {
                    append("전체 뱃지 ")
                }
            },
            color = LiftTheme.colorScheme.no9,
            style = LiftTheme.typography.no4
        )
        Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {

            Spacer(
                modifier = modifier
                    .weight(1f)
                    .height(48.dp)
                    .background(SkeletonBrush(), RoundedCornerShape(4.dp))
            )

            Spacer(
                modifier = modifier
                    .weight(1f)
                    .height(48.dp)
                    .background(SkeletonBrush(), RoundedCornerShape(4.dp))
            )
        }
        Row(
            modifier = modifier,
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Spacer(
                modifier = modifier
                    .width(64.dp)
                    .height(18.dp)
                    .background(SkeletonBrush(), RoundedCornerShape(4.dp))
            )
            Spacer(
                modifier = modifier
                    .weight(1f)
                    .height(18.dp)
                    .background(SkeletonBrush(), RoundedCornerShape(4.dp))
            )
        }
    }
}

