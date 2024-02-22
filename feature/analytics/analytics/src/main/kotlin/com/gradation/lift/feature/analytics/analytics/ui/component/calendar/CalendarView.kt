package com.gradation.lift.feature.analytics.analytics.ui.component.calendar

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import com.gradation.lift.designsystem.component.text.LiftText
import com.gradation.lift.designsystem.component.text.LiftTextStyle
import com.gradation.lift.designsystem.theme.LiftTheme
import com.gradation.lift.feature.analytics.analytics.data.model.WeekDateHistoryCount
import com.gradation.lift.feature.analytics.analytics.data.state.AnalyticsScreenState
import com.gradation.lift.model.model.date.getWeekdayEntries
import kotlinx.datetime.toJavaLocalDate
import java.time.LocalDate


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CalendarView(
    modifier: Modifier = Modifier,
    selectedDate: kotlinx.datetime.LocalDate,
    calendar: Map<Int, List<WeekDateHistoryCount>>,
    analyticsScreenState: AnalyticsScreenState,
) {

    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(LiftTheme.space.space24)
    ) {
        Column(modifier = modifier.fillMaxWidth()) {
            Row(
                modifier = modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(LiftTheme.space.space2)
            ) {
                getWeekdayEntries(startMonday = false).map { it.getWeekdayName() }.forEach {
                    WeekDateTitleCard(modifier.weight(1f), it)
                }
            }
            HorizontalPager(
                modifier = modifier,
                state = analyticsScreenState.pagerState
            ) {
                Column(
                    modifier = modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(LiftTheme.space.space2)
                ) {
                    calendar.forEach { (week, dateInfo) ->
                        Row(
                            modifier = modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(
                                LiftTheme.space.space2,
                                Alignment.CenterHorizontally
                            ),
                        ) {
                            when (week) {
                                1 -> {
                                    run {
                                        val count = getWeekdayEntries().size - dateInfo.count()
                                        val targetMonth: LocalDate =
                                            selectedDate.toJavaLocalDate().minusMonths(1)
                                        val targetDate =
                                            targetMonth.withDayOfMonth(targetMonth.lengthOfMonth())
                                                .minusDays(count.toLong())

                                        repeat(count) {
                                            DisabledWeekDateCard(
                                                modifier = modifier.weight(1f),
                                                date = kotlinx.datetime.LocalDate(
                                                    targetDate.year,
                                                    targetDate.monthValue,
                                                    (targetDate.plusDays(it.toLong() + 1L)).dayOfMonth
                                                ),
                                            )
                                        }
                                    }

                                    dateInfo.forEach { weekDateHistoryCount ->
                                        WeekDateCard(modifier.weight(1f), weekDateHistoryCount)
                                    }
                                }

                                calendar.maxOf { it.key } -> {
                                    dateInfo.forEach { weekDateHistoryCount ->
                                        WeekDateCard(modifier.weight(1f), weekDateHistoryCount)
                                    }
                                    run {
                                        val targetMonth: LocalDate =
                                            selectedDate.toJavaLocalDate().plusMonths(1)
                                        val targetDate = targetMonth.withDayOfMonth(1)
                                        repeat(getWeekdayEntries().size - dateInfo.count()) {
                                            DisabledWeekDateCard(
                                                modifier.weight(1f),
                                                date = kotlinx.datetime.LocalDate(
                                                    targetDate.year,
                                                    targetDate.monthValue,
                                                    (targetDate.plusDays(it.toLong())).dayOfMonth
                                                ),
                                            )
                                        }
                                    }
                                }

                                else -> {
                                    dateInfo.forEach { weekDateHistoryCount ->
                                        WeekDateCard(modifier.weight(1f), weekDateHistoryCount)
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        Row(
            modifier = modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(LiftTheme.space.space20)
        ) {
            listOf(
                Pair("운동한 날", LiftTheme.colorScheme.no47),
                Pair("운동 2회", LiftTheme.colorScheme.no50),
                Pair("운동 3회 이상", LiftTheme.colorScheme.no49),
            ).forEach {
                Row(
                    modifier = modifier,
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(LiftTheme.space.space8)
                ) {
                    LiftText(
                        textStyle = LiftTextStyle.No7,
                        text = it.first,
                        color = LiftTheme.colorScheme.no3,
                        textAlign = TextAlign.Center
                    )
                    Spacer(
                        modifier = modifier
                            .size(LiftTheme.space.space6)
                            .background(it.second, RoundedCornerShape(LiftTheme.space.space2))
                    )
                }
            }
        }
    }
}


@Composable
fun WeekDateTitleCard(
    modifier: Modifier = Modifier,
    weekday: String,
) {
    Box(
        modifier = modifier
            .height(LiftTheme.space.space44)
            .background(LiftTheme.colorScheme.no5)
            .padding(LiftTheme.space.space4),
        contentAlignment = Alignment.Center
    ) {
        LiftText(
            textStyle = LiftTextStyle.No5,
            text = weekday,
            color = LiftTheme.colorScheme.no2,
            textAlign = TextAlign.Center
        )
    }
}


@Composable
fun WeekDateCard(
    modifier: Modifier = Modifier,
    workDateHistoryCount: WeekDateHistoryCount,
) {
    Box(
        modifier = modifier
            .height(LiftTheme.space.space44)
            .background(
                when (workDateHistoryCount.historyCount) {
                    0 -> LiftTheme.colorScheme.no17
                    1 -> LiftTheme.colorScheme.no47
                    2 -> LiftTheme.colorScheme.no50
                    else -> LiftTheme.colorScheme.no49
                }, RoundedCornerShape(size = LiftTheme.space.space6)
            )
            .padding(LiftTheme.space.space4),
        contentAlignment = Alignment.Center
    ) {
        LiftText(
            modifier = modifier,
            textStyle = LiftTextStyle.No5,
            text = workDateHistoryCount.weekDateMonth.date.dayOfMonth.toString(),
            color = if (workDateHistoryCount.historyCount == 0) LiftTheme.colorScheme.no2 else LiftTheme.colorScheme.no5,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun DisabledWeekDateCard(
    modifier: Modifier = Modifier,
    date: kotlinx.datetime.LocalDate,
) {
    Spacer(modifier = modifier.height(LiftTheme.space.space44))
}

