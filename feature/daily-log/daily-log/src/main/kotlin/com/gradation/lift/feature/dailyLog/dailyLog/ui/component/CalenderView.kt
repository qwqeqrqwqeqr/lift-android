package com.gradation.lift.feature.dailyLog.dailyLog.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gradation.lift.designsystem.R
import com.gradation.lift.ui.modifier.noRippleClickable
import com.gradation.lift.designsystem.resource.LiftIcon
import com.gradation.lift.designsystem.theme.LiftTheme
import com.gradation.lift.feature.dailyLog.dailyLog.data.model.HistoryScoreWeekDate
import kotlinx.datetime.LocalDate

@Composable
fun CalenderView(
    modifier:Modifier=Modifier,
    selectedDate: LocalDate,
    historyScoreByMonth: List<HistoryScoreWeekDate>,
    updateSelectedDate: (LocalDate) -> Unit,
    plusMonthSelectedDate: () -> Unit,
    minusMonthSelectedDate: () -> Unit,
) {
    Column {
        Image(
            painter = painterResource(id = R.drawable.calender),
            contentDescription = "calendar",
            modifier = modifier
                .size(54.dp)
                .align(Alignment.CenterHorizontally)
        )
        Spacer(modifier = modifier.padding(8.dp))
        Row(
            modifier = modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(
                18.dp,
                Alignment.CenterHorizontally
            ),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = minusMonthSelectedDate) {
                Icon(
                    painter = painterResource(LiftIcon.LeftArrowCircle),
                    contentDescription = "PreMonth",
                    tint = Color.Unspecified
                )
            }
            Text(
                text = "${selectedDate.monthNumber}월 운동일지",
                style = LiftTheme.typography.no1.copy(fontSize = 20.sp),
                color = LiftTheme.colorScheme.no9
            )
            IconButton(onClick = plusMonthSelectedDate) {
                Icon(
                    painter = painterResource(LiftIcon.RightArrowCircle),
                    contentDescription = "NextMonth",
                    tint = Color.Unspecified
                )
            }
        }
        Spacer(modifier = modifier.padding(8.dp))

        Column(
            modifier = modifier
                .background(
                    LiftTheme.colorScheme.no17,
                    RoundedCornerShape(12.dp)
                )
                .padding(8.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Row(
                modifier = modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(3.dp)
            ) {
                listOf("일", "월", "화", "수", "목", "금", "토").forEach {
                    Text(
                        text = it,
                        style = LiftTheme.typography.no2,
                        color = LiftTheme.colorScheme.no9,
                        textAlign = TextAlign.Center,
                        modifier = modifier.weight(1f)
                    )
                }
            }
            Spacer(modifier = modifier.padding(8.dp))
            with(historyScoreByMonth.groupBy { it.week }) {
                forEach {
                    Row(
                        modifier = modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(
                            4.dp
                        ),
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        if (it.key == keys.min()) {
                            repeat(7 - it.value.size) {
                                Box(
                                    modifier = modifier
                                        .weight(1f)
                                        .background(
                                            LiftTheme.colorScheme.no13,
                                        )
                                )
                            }
                        }
                        it.value.forEach {
                            Column(
                                modifier = modifier
                                    .border(
                                        if (selectedDate == it.date) 2.dp else 0.dp,
                                        color = if (selectedDate == it.date) LiftTheme.colorScheme.no4 else Color.Transparent,
                                        RoundedCornerShape(8.dp)
                                    )
                                    .background(
                                        LiftTheme.colorScheme.no5,
                                        RoundedCornerShape(8.dp)
                                    )
                                    .weight(1f)
                                    .padding(vertical = 8.dp)
                                    .noRippleClickable {
                                        updateSelectedDate(it.date)
                                    },
                                verticalArrangement = Arrangement.spacedBy(
                                    4.dp
                                ),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Text(
                                    text = it.date.dayOfMonth.toString(),
                                    color = LiftTheme.colorScheme.no9,
                                    style = LiftTheme.typography.no3
                                )
                                Row(horizontalArrangement = Arrangement.Center) {
                                    if (it.score == 0) {
                                        Image(
                                            painter = painterResource(id = R.drawable.mini_star),
                                            contentDescription = "star",
                                            alpha = 0f,
                                        )
                                    } else {
                                        repeat(it.score) {
                                            Image(
                                                painter = painterResource(id = R.drawable.mini_star),
                                                contentDescription = "star",
                                                modifier = modifier
                                            )
                                        }
                                    }
                                }
                            }
                        }
                        if (it.key == keys.max()) {
                            repeat(7 - it.value.size) {
                                Box(
                                    modifier = modifier
                                        .weight(1f)
                                        .background(
                                            LiftTheme.colorScheme.no13,
                                        )
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}