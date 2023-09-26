package com.gradation.lift.feature.badge.badge.component.fail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import com.gradation.lift.designsystem.theme.LiftTheme


@Composable
fun FailBadgeInfoView(
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
        Spacer(modifier = modifier.height(72.dp))
    }
}
