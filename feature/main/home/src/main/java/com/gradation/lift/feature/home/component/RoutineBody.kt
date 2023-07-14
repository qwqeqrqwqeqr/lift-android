package com.gradation.lift.feature.home.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import com.gradation.lift.designsystem.R
import com.gradation.lift.feature.home.data.WeekDateRoutineUiState
import com.gradation.lift.feature.home.data.WeekDateUiState
import kotlinx.datetime.LocalDate

@Composable
fun RoutineBody(
    modifier: Modifier = Modifier,
    currentDate: LocalDate,
    weekDateRoutineUiState: WeekDateRoutineUiState,
    weekDateUiState : WeekDateUiState,
    weekCardClick: (LocalDate) -> Unit,
) {
    Box(
        modifier = modifier
            .background(
                color = Color.White,
                shape = RoundedCornerShape(24.dp, 24.dp, 0.dp, 0.dp)
            )
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Column(
            modifier = modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.calander),
                contentDescription = "덤벨",
                Modifier.size(64.dp),
                alignment = Alignment.Center,
            )
            Text(
                text = buildAnnotatedString {
                    append("내가 만든 ")
                    withStyle(
                        style = SpanStyle(color = MaterialTheme.colorScheme.primary),
                    ) {
                        append("루틴 리스트")
                    }
                },
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.secondary,
                textAlign = TextAlign.Center,
            )
            Text(
                text = buildAnnotatedString {
                    append("오늘은 ")
                    withStyle(
                        style = SpanStyle(fontWeight = FontWeight.Bold),
                    ) {
                        append("${currentDate.monthNumber}월 ${currentDate.dayOfMonth}일")
                    }
                    append("이에요!\n" + "루틴을 선택해서 운동을 해봐요!")
                },
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSecondary,
                textAlign = TextAlign.Center,
            )
            Spacer(modifier = modifier.height(16.dp))

            WeekDateView(weekDateUiState=weekDateUiState,weekCardClick= weekCardClick)

            RoutineView(weekDateRoutineUiState= weekDateRoutineUiState)
        }
    }
}



