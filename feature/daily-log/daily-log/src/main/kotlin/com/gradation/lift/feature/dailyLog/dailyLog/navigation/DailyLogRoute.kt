package com.gradation.lift.feature.dailyLog.dailyLog.navigation

import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.gradation.lift.designsystem.R
import com.gradation.lift.designsystem.component.text.LiftText
import com.gradation.lift.designsystem.component.text.LiftTextStyle
import com.gradation.lift.designsystem.theme.LiftTheme
import com.gradation.lift.feature.dailyLog.dailyLog.data.HistoryDailyLogViewModel
import com.gradation.lift.feature.dailyLog.dailyLog.data.model.HistoryScoreWeekDate
import com.gradation.lift.feature.dailyLog.dailyLog.data.state.HistoryUiState
import com.gradation.lift.feature.dailyLog.dailyLog.ui.HistoryDailyLogScreen
import com.gradation.lift.model.model.history.History
import kotlinx.datetime.LocalDate

@Composable
internal fun DailyLogRoute(
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
    val updateSelectedHistoryIndex: (Int) -> Unit = viewModel.updateSelectedHistoryIndex()

    val scrollState: ScrollState = rememberScrollState()


    when (historyUiState) {
        HistoryUiState.Empty -> {
            Column(
                modifier = modifier
                    .fillMaxSize()
                    .background(LiftTheme.colorScheme.no5),
                verticalArrangement = Arrangement.spacedBy(
                    LiftTheme.space.space8,
                    Alignment.CenterVertically
                ),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    modifier = modifier.size(LiftTheme.space.space96),
                    painter = painterResource(id = R.drawable.open_box),
                    contentDescription = "emptyBox",
                )
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(LiftTheme.space.space8)
                ) {
                    LiftText(
                        textStyle = LiftTextStyle.No2,
                        text = "운동을 진행 하신 적이 존재하지 않네요.",
                        color = LiftTheme.colorScheme.no2,
                        textAlign = TextAlign.Center
                    )
                    LiftText(
                        textStyle = LiftTextStyle.No4,
                        text = "운동을 완료하고 운동일지를 확인해봐요",
                        color = LiftTheme.colorScheme.no2,
                        textAlign = TextAlign.Center
                    )
                }
            }
        }

        is HistoryUiState.Fail -> {
            Spacer(
                modifier = modifier
                    .fillMaxSize()
                    .background(LiftTheme.colorScheme.no5)
            )
        }

        HistoryUiState.Loading -> {
            Spacer(
                modifier = modifier
                    .fillMaxSize()
                    .background(LiftTheme.colorScheme.no5)
            )
        }

        is HistoryUiState.Success -> {
            HistoryDailyLogScreen(
                modifier,
                selectedDate,
                selectedHistoryList,
                selectedHistory,
                historyScoreByMonth,
                updateSelectedDate,
                plusMonthSelectedDate,
                minusMonthSelectedDate,
                updateSelectedHistoryIndex,
                scrollState
            )
        }
    }


}