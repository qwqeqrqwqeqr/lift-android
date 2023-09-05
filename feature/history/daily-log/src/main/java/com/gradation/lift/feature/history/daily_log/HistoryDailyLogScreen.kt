package com.gradation.lift.feature.history.daily_log

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.gradation.lift.designsystem.R
import com.gradation.lift.designsystem.extensions.noRippleClickable
import com.gradation.lift.designsystem.resource.LiftIcon
import com.gradation.lift.designsystem.theme.LiftMaterialTheme
import com.gradation.lift.designsystem.theme.LiftTheme
import com.gradation.lift.feature.history.daily_log.data.HistoryDailyLogViewModel
import com.gradation.lift.feature.history.daily_log.data.model.HistoryScoreWeekDate
import com.gradation.lift.feature.history.daily_log.data.state.HistoryUiState
import com.gradation.lift.model.model.history.History
import com.gradation.lift.ui.utils.toDayMonthText
import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.todayIn

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HistoryDailyLogRoute(
    modifier: Modifier = Modifier,
    viewModel: HistoryDailyLogViewModel = hiltViewModel(),
) {
    val historyUiState: HistoryUiState by viewModel.historyUiState.collectAsStateWithLifecycle()

    val selectedDate: LocalDate by viewModel.selectedDate.collectAsStateWithLifecycle()
    val selectedHistoryList: List<History> by viewModel.selectedHistoryList.collectAsStateWithLifecycle()
    val selectedHistory: History by viewModel.selectedHistory.collectAsStateWithLifecycle()
    val historyScoreByMonth: List<HistoryScoreWeekDate> by viewModel.historyScoreByMonth.collectAsStateWithLifecycle()

    val updateSelectedDate: (LocalDate) -> Unit = viewModel.updateSelectedDate()
    val plusMonthSelectedDate: () -> Unit = viewModel.plusMonthSelectedDate()
    val minusMonthSelectedDate: () -> Unit = viewModel.minusMonthSelectedDate()

    val scrollState: ScrollState = rememberScrollState()


    HistoryDailyLogScreen(
        modifier,
        selectedDate,
        selectedHistoryList,
        selectedHistory,
        historyScoreByMonth,
        updateSelectedDate,
        plusMonthSelectedDate,
        minusMonthSelectedDate,
        scrollState
    )

}

@Composable
fun HistoryDailyLogScreen(
    modifier: Modifier = Modifier,
    selectedDate: LocalDate,
    selectedHistoryList: List<History>,
    selectedHistory: History,
    historyScoreByMonth: List<HistoryScoreWeekDate>,
    updateSelectedDate: (LocalDate) -> Unit,
    plusMonthSelectedDate: () -> Unit,
    minusMonthSelectedDate: () -> Unit,
    scrollState: ScrollState,
) {
    Surface(color = LiftTheme.colorScheme.no17, modifier = modifier.fillMaxSize()) {

        Column(
            modifier = modifier
                .padding(top = 16.dp)
                .verticalScroll(scrollState)
        ) {
            Column(
                modifier = modifier
                    .background(
                        LiftTheme.colorScheme.no5,
                        shape = RoundedCornerShape(18.dp, 18.dp, 0.dp, 0.dp)
                    )
                    .padding(16.dp),
            ) {
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
                                horizontalArrangement = Arrangement.spacedBy(4.dp),
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
                                        verticalArrangement = Arrangement.spacedBy(4.dp),
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
                Spacer(modifier = modifier.padding(16.dp))
                Column {
                    if(selectedHistoryList.isEmpty()){

                    }else{
                        Text(
                            text = selectedDate.toDayMonthText(),
                            color = LiftTheme.colorScheme.no9,
                            style = LiftTheme.typography.no1
                        )
                    }


                }

                

            }
        }
    }
}

@Composable
@Preview
fun HistoryDailyLogScreenPreview() {
    LiftMaterialTheme {
        HistoryDailyLogScreen(
            Modifier,
            selectedDate = Clock.System.todayIn(TimeZone.currentSystemDefault()),
            selectedHistoryList = emptyList(),
            selectedHistory= History(),
            historyScoreByMonth = emptyList(),
            updateSelectedDate = {},
            plusMonthSelectedDate = {},
            minusMonthSelectedDate = {},
            rememberScrollState()
        )
    }
}