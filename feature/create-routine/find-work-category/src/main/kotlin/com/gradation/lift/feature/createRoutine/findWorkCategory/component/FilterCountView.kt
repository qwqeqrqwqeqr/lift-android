package com.gradation.lift.feature.createRoutine.findWorkCategory.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import com.gradation.lift.designsystem.theme.LiftTheme

@Composable
fun FilterCountView(
    modifier: Modifier = Modifier,
    filteredWorkCategoryCount: Int,
) {
    Surface(
        color = LiftTheme.colorScheme.no17,
        modifier = modifier.fillMaxWidth()
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = modifier.padding(horizontal = 12.dp, vertical = 2.dp)
        ) {
            Text(
                text = buildAnnotatedString {
                    append("총 ")
                    withStyle(
                        style = SpanStyle(
                            fontWeight = FontWeight(700)
                        ),
                    ) {
                        append("${filteredWorkCategoryCount}개")
                    }
                    append("의 운동")
                },
                style = LiftTheme.typography.no6,
                color = LiftTheme.colorScheme.no9,
            )
        }
    }

}