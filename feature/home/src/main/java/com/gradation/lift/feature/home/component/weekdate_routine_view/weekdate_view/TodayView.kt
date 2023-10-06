package com.gradation.lift.feature.home.component.weekdate_routine_view.weekdate_view

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import com.gradation.lift.designsystem.theme.LiftTheme
import kotlinx.datetime.LocalDate

@Composable
fun TodayView(
    modifier: Modifier = Modifier,
    today: LocalDate,
) {
    Text(
        text = "내 루틴",
        style = LiftTheme.typography.no1,
        color = LiftTheme.colorScheme.no9,
    )
    Text(
        text = buildAnnotatedString {
            append("오늘은 ")
            withStyle(
                style = SpanStyle(fontWeight = FontWeight.Bold),
            ) {
                append("${today.monthNumber}월 ${today.dayOfMonth}일")
            }
            append("이에요!")
        },
        style = LiftTheme.typography.no4,
        color = LiftTheme.colorScheme.no9,
    )
    Spacer(modifier = modifier.padding(8.dp))
}