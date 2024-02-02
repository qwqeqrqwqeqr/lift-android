package com.gradation.lift.feature.history.history.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.style.TextAlign
import com.gradation.lift.designsystem.component.text.LiftText
import com.gradation.lift.designsystem.component.text.LiftTextStyle
import com.gradation.lift.designsystem.theme.LiftTheme
import com.gradation.lift.feature.history.history.data.model.WeekDateHistoryCount
import com.gradation.lift.feature.history.history.data.state.HistoryScreenState
import com.gradation.lift.feature.history.history.ui.component.CalendarView
import com.gradation.lift.feature.history.history.ui.component.EmptyHistoryView
import com.gradation.lift.feature.history.history.ui.component.HeaderView
import com.gradation.lift.feature.history.history.ui.component.HistoryCountTab
import com.gradation.lift.feature.history.history.ui.component.historyContent.HistoryContent
import com.gradation.lift.model.model.date.toWeekday
import com.gradation.lift.model.model.history.History
import kotlinx.datetime.LocalDate

@Composable
fun HistoryScreen(
    modifier: Modifier = Modifier,
    today: LocalDate,
    selectedDate: LocalDate,
    selectedTabIndex: Int,
    calendar: Map<Int, List<WeekDateHistoryCount>>,
    selectedHistoryList: List<History>,
    updateSelectedTabIndex: (Int) -> Unit,
    updateSelectedDate: (LocalDate) -> Unit,
    navigateHistoryToUpdateInfoInHistoryGraph: (String, Int, Int, String) -> Unit,
    historyScreenState: HistoryScreenState,
) {

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    listOf(
                        LiftTheme.colorScheme.no5,
                        LiftTheme.colorScheme.no5,
                        LiftTheme.colorScheme.no5,
                        LiftTheme.colorScheme.no1,
                    )
                )
            )
            .padding(top = LiftTheme.space.space40, bottom = LiftTheme.space.space60),
        state = historyScreenState.lazyListState
    ) {
        item {
            Column(modifier = modifier.background(LiftTheme.colorScheme.no5)) {
                HeaderView(modifier, selectedDate, historyScreenState)
                Spacer(modifier = modifier.height(LiftTheme.space.space24))
                CalendarView(
                    modifier,
                    today,
                    selectedDate,
                    calendar,
                    updateSelectedDate,
                    historyScreenState
                )
                Spacer(modifier = modifier.height(LiftTheme.space.space28))
                Spacer(
                    modifier = modifier
                        .height(LiftTheme.space.space8)
                        .fillMaxWidth()
                        .background(LiftTheme.colorScheme.no17)
                )
                Spacer(modifier = modifier.height(LiftTheme.space.space28))
            }
        }
        item {
            when (selectedHistoryList.size) {
                0 ->
                    Column(
                        modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.spacedBy(LiftTheme.space.space24)
                    ) {
                        LiftText(
                            modifier = modifier.padding(horizontal = LiftTheme.space.space20),
                            textStyle = LiftTextStyle.No2,
                            text = "${selectedDate.dayOfMonth}일 ${
                                selectedDate.toWeekday().getWeekdayName()
                            }요일",
                            color = LiftTheme.colorScheme.no9,
                            textAlign = TextAlign.Center
                        )
                        EmptyHistoryView(
                            modifier, selectedDate, historyScreenState
                        )
                    }

                1 -> {

                    Column(
                        modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.spacedBy(LiftTheme.space.space24)
                    ) {
                        LiftText(
                            modifier = modifier.padding(horizontal = LiftTheme.space.space20),
                            textStyle = LiftTextStyle.No2,
                            text = "${selectedDate.dayOfMonth}일 ${
                                selectedDate.toWeekday().getWeekdayName()
                            }요일",
                            color = LiftTheme.colorScheme.no9,
                            textAlign = TextAlign.Center
                        )
                        Column(
                            modifier = modifier.fillMaxWidth(),
                            verticalArrangement = Arrangement.spacedBy(LiftTheme.space.space20)
                        ) {
                            HistoryContent(
                                modifier,
                                selectedHistoryList[selectedTabIndex],
                                navigateHistoryToUpdateInfoInHistoryGraph,
                                historyScreenState
                            )
                        }
                    }
                }

                    else -> {

                        Column(
                            modifier.fillMaxSize(),
                            verticalArrangement = Arrangement.spacedBy(LiftTheme.space.space24)
                        ) {
                            LiftText(
                                modifier = modifier.padding(horizontal = LiftTheme.space.space20),
                                textStyle = LiftTextStyle.No2,
                                text = "${selectedDate.dayOfMonth}일 ${
                                    selectedDate.toWeekday().getWeekdayName()
                                }요일",
                                color = LiftTheme.colorScheme.no9,
                                textAlign = TextAlign.Center
                            )
                            Column(
                                modifier = modifier.fillMaxWidth(),
                                verticalArrangement = Arrangement.spacedBy(LiftTheme.space.space20)
                            ) {
                                HistoryCountTab(
                                    modifier,
                                    selectedTabIndex,
                                    selectedHistoryList,
                                    updateSelectedTabIndex
                                )
                                HistoryContent(
                                    modifier,
                                    selectedHistoryList[selectedTabIndex],
                                    navigateHistoryToUpdateInfoInHistoryGraph,
                                    historyScreenState
                                )
                            }
                        }
                }
            }
        }
    }
}




