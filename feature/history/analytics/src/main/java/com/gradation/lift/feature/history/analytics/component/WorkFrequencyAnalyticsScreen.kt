package com.gradation.lift.feature.history.analytics.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.unit.sp
import com.gradation.lift.designsystem.resource.LiftIcon
import com.gradation.lift.designsystem.theme.LiftTheme
import com.gradation.lift.feature.history.analytics.data.model.WorkFrequencyWeekDate
import com.gradation.lift.feature.history.analytics.weekToText
import kotlinx.datetime.LocalDate

@Composable
fun WorkFrequencyAnalyticsScreen(
    modifier: Modifier = Modifier,
    selectedMonth: LocalDate,
    historyCountByThisMonth: Int,
    workFrequencyByWeek: List<WorkFrequencyWeekDate>,
    plusSelectedMonth: () -> Unit,
    minusSelectedMonth: () -> Unit,
){
    Column(
        modifier = modifier
            .background(
                LiftTheme.colorScheme.no5,
                shape = RoundedCornerShape(18.dp, 18.dp, 0.dp, 0.dp)
            )
            .padding(16.dp)
    ) {
        Spacer(modifier = modifier.padding(16.dp))
        Text(
            text = buildAnnotatedString {
                append("이번달에는 ")

                withStyle(
                    style = SpanStyle(color = LiftTheme.colorScheme.no4),
                ) {
                    append("${historyCountByThisMonth}회")
                }
                append(" 운동했어요")
            },
            modifier = modifier.fillMaxWidth(),
            color = LiftTheme.colorScheme.no9,
            style = LiftTheme.typography.no1,
            textAlign = TextAlign.Center
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
            IconButton(onClick = minusSelectedMonth) {
                Icon(
                    painter = painterResource(LiftIcon.LeftArrowCircle),
                    contentDescription = "PreMonth",
                    tint = Color.Unspecified
                )
            }
            Text(
                text = selectedMonth.monthNumber.toString(),
                style = LiftTheme.typography.no1.copy(fontSize = 20.sp),
                color = LiftTheme.colorScheme.no9
            )
            IconButton(onClick = plusSelectedMonth) {
                Icon(
                    painter = painterResource(LiftIcon.RightArrowCircle),
                    contentDescription = "NextMonth",
                    tint = Color.Unspecified
                )
            }
        }
        Spacer(modifier = modifier.padding(8.dp))

        Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
            Row(
                modifier = modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(3.dp)
            ) {
                listOf("","일", "월", "화", "수", "목", "금", "토").forEach {
                    Text(
                        text = it,
                        style = LiftTheme.typography.no5.copy(fontWeight = FontWeight(700)),
                        color = LiftTheme.colorScheme.no9,
                        textAlign = TextAlign.Center,
                        modifier = modifier.weight(1f)
                    )
                }
            }
            with(workFrequencyByWeek.groupBy { it.week }) {
                forEach {
                    Row(
                        modifier = modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(3.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = it.key.weekToText(),
                            style = LiftTheme.typography.no5.copy(
                                fontWeight = FontWeight(
                                    700
                                )
                            ),
                            color = LiftTheme.colorScheme.no9,
                            textAlign = TextAlign.Center,
                            modifier = modifier.weight(1f)
                        )
                        if (it.key == keys.min()) {
                            repeat(7 - it.value.size) {
                                Box(
                                    modifier = modifier
                                        .weight(1f)
                                        .width(36.dp)
                                        .height(36.dp)
                                        .background(
                                            LiftTheme.colorScheme.no5,
                                            shape = RoundedCornerShape(6.dp)
                                        )
                                )
                            }
                        }
                        it.value.forEach {
                            when (it.frequency) {
                                0 -> {
                                    Box(
                                        modifier = modifier
                                            .weight(1f)
                                            .width(36.dp)
                                            .height(36.dp)
                                            .background(
                                                LiftTheme.colorScheme.no17,
                                                shape = RoundedCornerShape(6.dp)
                                            )
                                    )
                                }
                                1 -> {
                                    Box(
                                        modifier = modifier
                                            .weight(1f)
                                            .width(36.dp)
                                            .height(36.dp)
                                            .background(
                                                LiftTheme.colorScheme.no26,
                                                shape = RoundedCornerShape(6.dp)
                                            )
                                    )
                                }
                                else -> {
                                    Box(
                                        modifier = modifier
                                            .weight(1f)
                                            .width(36.dp)
                                            .height(36.dp)
                                            .background(
                                                LiftTheme.colorScheme.no4,
                                                shape = RoundedCornerShape(6.dp)
                                            )
                                    )
                                }
                            }

                        }
                        if (it.key == keys.max()) {
                            repeat(7 - it.value.size) {
                                Box(
                                    modifier = modifier
                                        .weight(1f)
                                        .width(35.dp)
                                        .height(35.dp)
                                        .background(
                                            LiftTheme.colorScheme.no5,
                                            shape = RoundedCornerShape(6.dp)
                                        )
                                )
                            }
                        }
                    }
                }
            }
        }
        Spacer(modifier = modifier.padding(8.dp))
        Row(
            horizontalArrangement = Arrangement.spacedBy(3.dp, Alignment.CenterHorizontally),
            verticalAlignment = Alignment.CenterVertically,
            modifier = modifier.align(Alignment.End)
        ) {
            Box(
                modifier = modifier
                    .width(18.dp)
                    .height(18.dp)
                    .background(
                        LiftTheme.colorScheme.no17,
                        shape = RoundedCornerShape(6.dp)
                    ),
                contentAlignment = Alignment.Center

            ) {
                Text(
                    text = "0",
                    style = LiftTheme.typography.no7,
                    color = LiftTheme.colorScheme.no5
                )
            }
            Box(
                modifier = modifier
                    .width(18.dp)
                    .height(18.dp)
                    .background(
                        LiftTheme.colorScheme.no26,
                        shape = RoundedCornerShape(6.dp)
                    ),
                contentAlignment = Alignment.Center

            ) {
                Text(
                    text = "1",
                    style = LiftTheme.typography.no7,
                    color = LiftTheme.colorScheme.no5
                )
            }
            Box(
                modifier = modifier
                    .width(18.dp)
                    .height(18.dp)
                    .background(
                        LiftTheme.colorScheme.no4,
                        shape = RoundedCornerShape(6.dp)
                    ),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "2",
                    style = LiftTheme.typography.no7,
                    color = LiftTheme.colorScheme.no5
                )
            }
            Text(
                text = "회",
                style = LiftTheme.typography.no7,
                color = LiftTheme.colorScheme.no11
            )
        }
    }
}