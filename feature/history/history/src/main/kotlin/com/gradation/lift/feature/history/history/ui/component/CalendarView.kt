package com.gradation.lift.feature.history.history.ui.component

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.gradation.lift.designsystem.component.text.LiftText
import com.gradation.lift.designsystem.component.text.LiftTextStyle
import com.gradation.lift.designsystem.theme.LiftTheme
import com.gradation.lift.feature.history.history.data.model.WeekDateHistoryCount
import com.gradation.lift.feature.history.history.data.state.HistoryScreenState
import com.gradation.lift.model.model.date.Weekday
import com.gradation.lift.model.model.date.getWeekdayEntries
import com.gradation.lift.model.model.date.toWeekday
import com.gradation.lift.ui.modifier.noRippleClickable
import kotlinx.datetime.toJavaLocalDate
import java.time.LocalDate


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CalendarView(
    modifier: Modifier = Modifier,
    today: kotlinx.datetime.LocalDate,
    selectedDate: kotlinx.datetime.LocalDate,
    calendar: Map<Int, List<WeekDateHistoryCount>>,
    updateSelectedDate: (kotlinx.datetime.LocalDate) -> Unit,
    historyScreenState: HistoryScreenState,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = LiftTheme.space.space20),
    ) {
        Column(modifier = modifier.fillMaxWidth()) {
            Row(
                modifier = modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(LiftTheme.space.space2)
            ) {
                getWeekdayEntries(startMonday = false).map { it.getWeekdayName() }.forEach {
                    Column(
                        modifier = modifier
                            .weight(1f)
                            .padding(LiftTheme.space.space4),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(LiftTheme.space.space4)
                    ) {
                        LiftText(
                            textStyle = LiftTextStyle.No5,
                            text = it,
                            color = LiftTheme.colorScheme.no2,
                            textAlign = TextAlign.Center
                        )
                        Spacer(modifier = modifier.height(LiftTheme.space.space6))
                    }
                }
            }
            HorizontalPager(
                modifier = modifier,
                state = historyScreenState.pagerState
            ) {
                Column(modifier = modifier.fillMaxWidth()) {
                    calendar.forEach { (week, dateInfo) ->
                        Row(
                            modifier = modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(LiftTheme.space.space2),
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
                                                updateSelectedDate = updateSelectedDate
                                            )
                                        }
                                    }

                                    dateInfo.forEach { weekDateHistoryCount ->
                                        Column(modifier = modifier
                                            .border(
                                                1.5.dp,
                                                if (selectedDate == weekDateHistoryCount.weekDateMonth.date) LiftTheme.colorScheme.no53
                                                else Color.Transparent,
                                                RoundedCornerShape(LiftTheme.space.space6)
                                            )
                                            .noRippleClickable {
                                                updateSelectedDate(weekDateHistoryCount.weekDateMonth.date)

                                            }
                                            .padding(vertical = LiftTheme.space.space4)
                                            .weight(1f),
                                            horizontalAlignment = Alignment.CenterHorizontally,
                                            verticalArrangement = Arrangement.spacedBy(LiftTheme.space.space4)) {
                                            if (today == weekDateHistoryCount.weekDateMonth.date) Box(
                                                modifier = modifier
                                                    .size(LiftTheme.space.space28)
                                                    .background(
                                                        LiftTheme.colorScheme.no4, CircleShape
                                                    ), contentAlignment = Alignment.Center
                                            ) {
                                                LiftText(
                                                    textStyle = LiftTextStyle.No5,
                                                    text = weekDateHistoryCount.weekDateMonth.date.dayOfMonth.toString(),
                                                    color = LiftTheme.colorScheme.no5,
                                                    textAlign = TextAlign.Center
                                                )
                                            }
                                            else Box(
                                                modifier = modifier.size(LiftTheme.space.space28),
                                                contentAlignment = Alignment.Center
                                            ) {
                                                LiftText(
                                                    textStyle = LiftTextStyle.No5,
                                                    text = weekDateHistoryCount.weekDateMonth.date.dayOfMonth.toString(),
                                                    color = if (weekDateHistoryCount.weekDateMonth.date.toWeekday() == Weekday.Sunday()) LiftTheme.colorScheme.no12 else LiftTheme.colorScheme.no9,
                                                    textAlign = TextAlign.Center
                                                )
                                            }
                                            HistoryCountLabel(
                                                modifier, weekDateHistoryCount.historyCount
                                            )
                                        }
                                    }
                                }

                                calendar.maxOf { it.key } -> {
                                    dateInfo.forEach { weekDateHistoryCount ->
                                        Column(modifier = modifier
                                            .border(
                                                1.5.dp,
                                                if (selectedDate == weekDateHistoryCount.weekDateMonth.date) LiftTheme.colorScheme.no53
                                                else Color.Transparent,
                                                RoundedCornerShape(LiftTheme.space.space6)
                                            )
                                            .noRippleClickable {
                                                updateSelectedDate(weekDateHistoryCount.weekDateMonth.date)

                                            }
                                            .padding(vertical = LiftTheme.space.space4)
                                            .weight(1f),
                                            horizontalAlignment = Alignment.CenterHorizontally,
                                            verticalArrangement = Arrangement.spacedBy(LiftTheme.space.space4)) {
                                            if (today == weekDateHistoryCount.weekDateMonth.date) Box(
                                                modifier = modifier
                                                    .size(LiftTheme.space.space28)
                                                    .background(
                                                        LiftTheme.colorScheme.no4, CircleShape
                                                    ), contentAlignment = Alignment.Center
                                            ) {
                                                LiftText(
                                                    textStyle = LiftTextStyle.No5,
                                                    text = weekDateHistoryCount.weekDateMonth.date.dayOfMonth.toString(),
                                                    color = LiftTheme.colorScheme.no5,
                                                    textAlign = TextAlign.Center
                                                )
                                            }
                                            else Box(
                                                modifier = modifier.size(LiftTheme.space.space28),
                                                contentAlignment = Alignment.Center
                                            ) {
                                                LiftText(
                                                    textStyle = LiftTextStyle.No5,
                                                    text = weekDateHistoryCount.weekDateMonth.date.dayOfMonth.toString(),
                                                    color = if (weekDateHistoryCount.weekDateMonth.date.toWeekday() == Weekday.Sunday()) LiftTheme.colorScheme.no12 else LiftTheme.colorScheme.no9,
                                                    textAlign = TextAlign.Center
                                                )
                                            }
                                            HistoryCountLabel(
                                                modifier, weekDateHistoryCount.historyCount
                                            )
                                        }
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
                                                updateSelectedDate = updateSelectedDate
                                            )
                                        }
                                    }
                                }

                                else -> {
                                    dateInfo.forEach { weekDateHistoryCount ->
                                        Column(modifier = modifier
                                            .border(
                                                1.5.dp,
                                                if (selectedDate == weekDateHistoryCount.weekDateMonth.date) LiftTheme.colorScheme.no53
                                                else Color.Transparent,
                                                RoundedCornerShape(LiftTheme.space.space6)
                                            )
                                            .noRippleClickable {
                                                updateSelectedDate(weekDateHistoryCount.weekDateMonth.date)
                                            }
                                            .padding(vertical = LiftTheme.space.space4)
                                            .weight(1f),
                                            horizontalAlignment = Alignment.CenterHorizontally,
                                            verticalArrangement = Arrangement.spacedBy(LiftTheme.space.space4)) {
                                            if (today == weekDateHistoryCount.weekDateMonth.date) Box(
                                                modifier = modifier
                                                    .size(LiftTheme.space.space28)
                                                    .background(
                                                        LiftTheme.colorScheme.no4, CircleShape
                                                    ), contentAlignment = Alignment.Center
                                            ) {
                                                LiftText(
                                                    textStyle = LiftTextStyle.No5,
                                                    text = weekDateHistoryCount.weekDateMonth.date.dayOfMonth.toString(),
                                                    color = LiftTheme.colorScheme.no5,
                                                    textAlign = TextAlign.Center
                                                )
                                            }
                                            else Box(
                                                modifier = modifier.size(LiftTheme.space.space28),
                                                contentAlignment = Alignment.Center
                                            ) {
                                                LiftText(
                                                    textStyle = LiftTextStyle.No5,
                                                    text = weekDateHistoryCount.weekDateMonth.date.dayOfMonth.toString(),
                                                    color = if (weekDateHistoryCount.weekDateMonth.date.toWeekday() == Weekday.Sunday()) LiftTheme.colorScheme.no12 else LiftTheme.colorScheme.no9,
                                                    textAlign = TextAlign.Center
                                                )
                                            }
                                            HistoryCountLabel(
                                                modifier, weekDateHistoryCount.historyCount
                                            )
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        Spacer(modifier = modifier.height(LiftTheme.space.space16))
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
                            .background(it.second, CircleShape)
                    )
                }
            }
        }
    }
}


@Composable
fun DisabledWeekDateCard(
    modifier: Modifier = Modifier,
    date: kotlinx.datetime.LocalDate,
    updateSelectedDate: (kotlinx.datetime.LocalDate) -> Unit,
) {
    Column(
        modifier = modifier
            .noRippleClickable {
                updateSelectedDate(date)
            }
            .padding(vertical = LiftTheme.space.space8),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(LiftTheme.space.space4)
    ) {
        LiftText(
            textStyle = LiftTextStyle.No5,
            text = date.dayOfMonth.toString(),
            color = if (date
                    .toWeekday() == Weekday.Sunday()
            ) LiftTheme.colorScheme.no52 else LiftTheme.colorScheme.no6,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.size(LiftTheme.space.space6))
    }
}


@Composable
fun HistoryCountLabel(
    modifier: Modifier = Modifier,
    historyCount: Int,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = LiftTheme.space.space4),
        horizontalArrangement = Arrangement.spacedBy(LiftTheme.space.space4)
    ) {
        when (historyCount) {
            0 -> Spacer(modifier = Modifier.size(LiftTheme.space.space6))
            1 -> {
                Spacer(
                    modifier = Modifier
                        .size(LiftTheme.space.space6)
                        .background(
                            LiftTheme.colorScheme.no47, CircleShape
                        )
                )
            }

            2 -> {
                Spacer(
                    modifier = Modifier
                        .size(LiftTheme.space.space6)
                        .background(
                            LiftTheme.colorScheme.no47, CircleShape
                        )
                )
                Spacer(
                    modifier = Modifier
                        .size(LiftTheme.space.space6)
                        .background(
                            LiftTheme.colorScheme.no50, CircleShape
                        )
                )
            }

            else -> {
                Spacer(
                    modifier = Modifier
                        .size(LiftTheme.space.space6)
                        .background(
                            LiftTheme.colorScheme.no47, CircleShape
                        )
                )
                Spacer(
                    modifier = Modifier
                        .size(LiftTheme.space.space6)
                        .background(
                            LiftTheme.colorScheme.no50, CircleShape
                        )
                )
                Spacer(
                    modifier = Modifier
                        .size(LiftTheme.space.space6)
                        .background(
                            LiftTheme.colorScheme.no49, CircleShape
                        )
                )
            }
        }
    }
}