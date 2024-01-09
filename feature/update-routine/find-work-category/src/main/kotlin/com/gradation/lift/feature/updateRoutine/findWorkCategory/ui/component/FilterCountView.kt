package com.gradation.lift.feature.updateRoutine.findWorkCategory.ui.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import com.gradation.lift.designsystem.theme.LiftTheme
import com.gradation.lift.feature.updateRoutine.findWorkCategory.data.model.TagWorkCategory

@Composable
fun FilterCountView(
    modifier: Modifier = Modifier,
    workCategoryList: List<TagWorkCategory>,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = LiftTheme.space.space20),
    ) {
        Text(
            text = buildAnnotatedString {
                append("총 ")
                withStyle(
                    style = SpanStyle(
                        fontWeight = FontWeight(700)
                    ),
                ) {
                    append("${workCategoryList.size}개")
                }
                append("의 운동")
            },
            style = LiftTheme.typography.no6,
            color = LiftTheme.colorScheme.no9,
        )
    }

}