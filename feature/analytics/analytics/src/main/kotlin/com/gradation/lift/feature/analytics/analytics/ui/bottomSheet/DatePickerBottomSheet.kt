package com.gradation.lift.feature.analytics.analytics.ui.bottomSheet

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import com.gradation.lift.designsystem.component.bottomSheet.LiftBottomSheet
import com.gradation.lift.designsystem.component.button.LiftDefaultButton
import com.gradation.lift.designsystem.component.button.LiftSolidButton
import com.gradation.lift.designsystem.component.text.LiftText
import com.gradation.lift.designsystem.component.text.LiftTextStyle
import com.gradation.lift.designsystem.resource.LiftIcon
import com.gradation.lift.designsystem.theme.LiftTheme
import com.gradation.lift.feature.analytics.analytics.data.state.AnalyticsScreenState
import com.gradation.lift.ui.modifier.noRippleClickable
import kotlinx.datetime.LocalDate

/**
 * 날짜 선택 바텀 시트
 * @since 2024-01-28 23:04:10
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun DatePickerBottomSheet(
    modifier: Modifier = Modifier,
    selectedDate: LocalDate,
    updateSelectedDate: (LocalDate) -> Unit,
    analyticsScreenState: AnalyticsScreenState,
) {

    LiftBottomSheet(
        modifier = modifier,
        onDismissRequest = { analyticsScreenState.updateDatePickerBottomSheetView(false) },
        dragHandle = null,
    ) {
        var currentYearValue: Int by remember { mutableIntStateOf(selectedDate.year) }
        var currentMonthValue: Int by remember { mutableIntStateOf(selectedDate.monthNumber) }
        var currentDayValue: Int by remember { mutableIntStateOf(selectedDate.dayOfMonth) }

        val updateCurrentYearValue: (Int) -> Unit = { currentYearValue = it }
        val updateCurrentMonthValue: (Int) -> Unit = { currentMonthValue = it }
        val updateCurrentDayValue: (Int) -> Unit = { currentDayValue = it }

        Column(
            modifier = modifier
                .padding(
                    start = LiftTheme.space.space20,
                    end = LiftTheme.space.space20,
                    bottom = LiftTheme.space.space20
                )
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(LiftTheme.space.space28)
        ) {
            Row(
                modifier = modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(LiftTheme.space.space2)
                ) {
                    LiftText(
                        color = LiftTheme.colorScheme.no9,
                        textStyle = LiftTextStyle.No2,
                        textAlign = TextAlign.Start,
                        text = "${selectedDate.year}년 ${selectedDate.monthNumber}월 ${selectedDate.dayOfMonth}일"
                    )
                }
                Icon(
                    modifier = modifier
                        .size(LiftTheme.space.space10)
                        .noRippleClickable {
                            analyticsScreenState.updateDatePickerBottomSheetView(false)
                        },
                    painter = painterResource(LiftIcon.Close),
                    contentDescription = "Close",
                    tint = LiftTheme.colorScheme.no9,
                )
            }
            DateSelectionView(
                modifier,
                currentYearValue,
                currentMonthValue,
                currentDayValue,
                updateCurrentYearValue,
                updateCurrentMonthValue,
                updateCurrentDayValue,
                analyticsScreenState
            )
            Row(
                horizontalArrangement = Arrangement.spacedBy(LiftTheme.space.space12)
            ) {
                LiftDefaultButton(
                    modifier.weight(1f),
                    "취소",
                ) {
                    analyticsScreenState.updateDatePickerBottomSheetView(false)
                }

                LiftSolidButton(
                    modifier.weight(1f),
                    "저장",
                ) {
                    updateSelectedDate(
                        LocalDate(
                            currentYearValue,
                            currentMonthValue,
                            currentDayValue
                        )
                    )
                    analyticsScreenState.updateDatePickerBottomSheetView(false)
                }
            }
        }
    }
}

@Composable
fun DateSelectionView(
    modifier: Modifier = Modifier,
    currentYearValue: Int,
    currentMonthValue: Int,
    currentDayValue: Int,
    updateCurrentYearValue: (Int) -> Unit,
    updateCurrentMonthValue: (Int) -> Unit,
    updateCurrentDayValue: (Int) -> Unit,
    analyticsScreenState: AnalyticsScreenState,
) {
    val yearListState =
        rememberLazyListState(initialFirstVisibleItemIndex = Int.MAX_VALUE / 2 + (currentYearValue - 2024))
    val monthListState =
        rememberLazyListState(initialFirstVisibleItemIndex = Int.MAX_VALUE / 2 - 5 + currentMonthValue)
    val dayListState =
        rememberLazyListState(initialFirstVisibleItemIndex = Int.MAX_VALUE / 2 + (currentDayValue - 5))

    val yearList: MutableState<List<Int>> =
        remember { mutableStateOf(analyticsScreenState.yearRange.toList()) }
    val monthList: MutableState<List<Int>> = remember { mutableStateOf((1..12).toList()) }
    val dayList = remember(currentYearValue, currentMonthValue) {
        derivedStateOf {
            (if (currentYearValue % 4 == 0 && currentMonthValue == 2) (1..29)
            else if (currentMonthValue in listOf(1, 3, 5, 7, 8, 10, 12)) (1..31)
            else (1..30)).toList()
        }
    }

    val interactionSource: MutableInteractionSource = remember { MutableInteractionSource() }
    val isPressed: Boolean by interactionSource.collectIsPressedAsState()

    val textColor: Color by animateColorAsState(
        targetValue = if (isPressed) LiftTheme.colorScheme.no4 else LiftTheme.colorScheme.no9,
        label = "textColorAnimation"
    )

    /**
     * 스크롤 수행 정지 시 현재 값 업데이트
     */
    LaunchedEffect(key1 = !yearListState.isScrollInProgress) {
        updateCurrentYearValue(currentYearValue)
        yearListState.animateScrollToItem(index = yearListState.firstVisibleItemIndex)
    }
    LaunchedEffect(key1 = !monthListState.isScrollInProgress) {
        updateCurrentMonthValue(currentMonthValue)
        monthListState.animateScrollToItem(index = monthListState.firstVisibleItemIndex)
    }
    LaunchedEffect(key1 = !dayListState.isScrollInProgress) {
        updateCurrentDayValue(currentDayValue)
        dayListState.animateScrollToItem(index = dayListState.firstVisibleItemIndex)
    }




    Row(
        horizontalArrangement = Arrangement.SpaceAround,
        modifier = modifier
            .fillMaxWidth()
            .height(LiftTheme.space.space100)

    ) {
        LazyColumn(
            modifier = modifier
                .weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally,
            state = yearListState
        ) {
            items(count = Int.MAX_VALUE) { item ->
                val index = item % yearList.value.size

                val currentValue by remember(yearListState) { derivedStateOf { yearListState.firstVisibleItemIndex + 1 } }
                LaunchedEffect(currentValue) {
                    if (item == currentValue) {
                        updateCurrentYearValue(yearList.value[index])
                        analyticsScreenState.hapticFeedbackType.performHapticFeedback(
                            HapticFeedbackType.TextHandleMove
                        )
                    }
                }
                Spacer(modifier = modifier.height(LiftTheme.space.space10))
                AnimatedContent(
                    targetState = item == currentValue,
                    label = "textAlphaAnimation"
                ) { selected ->
                    LiftText(
                        text = yearList.value[index].toString() + "년",
                        modifier = modifier
                            .alpha(if (selected) 1f else 0.6f),
                        textStyle = LiftTextStyle.No3,
                        color = textColor,
                        textAlign = TextAlign.Center
                    )
                }
                Spacer(modifier = modifier.height(LiftTheme.space.space10))
            }
        }
        LazyColumn(
            modifier = modifier.weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally,
            state = monthListState
        ) {
            items(count = Int.MAX_VALUE) { item ->
                val index = item % monthList.value.size
                val currentValue by remember(monthListState) { derivedStateOf { monthListState.firstVisibleItemIndex + 1 } }
                LaunchedEffect(currentValue) {
                    if (item == currentValue) {
                        updateCurrentMonthValue(monthList.value[index])
                        analyticsScreenState.hapticFeedbackType.performHapticFeedback(
                            HapticFeedbackType.TextHandleMove
                        )
                    }
                }
                Spacer(modifier = modifier.height(LiftTheme.space.space10))
                AnimatedContent(
                    targetState = currentValue == item,
                    label = "textAlphaAnimation"
                ) { selected ->
                    LiftText(
                        text = monthList.value[index].toString(),
                        modifier = modifier.alpha(if (selected) 1f else 0.6f),
                        textStyle = LiftTextStyle.No3,
                        color = textColor,
                        textAlign = TextAlign.Center
                    )
                }
                Spacer(modifier = modifier.height(LiftTheme.space.space10))
            }
        }
        LazyColumn(
            modifier = modifier.weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally,
            state = dayListState
        ) {
            items(count = Int.MAX_VALUE) { item ->
                val index = item % dayList.value.size
                val currentValue by remember(dayListState) { derivedStateOf { dayListState.firstVisibleItemIndex + 1 } }
                LaunchedEffect(currentValue) {
                    if (item == currentValue) {
                        updateCurrentDayValue(dayList.value[index])
                        analyticsScreenState.hapticFeedbackType.performHapticFeedback(
                            HapticFeedbackType.TextHandleMove
                        )
                    }
                }
                Spacer(modifier = modifier.height(LiftTheme.space.space10))
                AnimatedContent(
                    targetState = currentValue == item,
                    label = "textAlphaAnimation"
                ) { selected ->
                    LiftText(
                        text = dayList.value[index].toString(),
                        modifier = modifier.alpha(if (selected) 1f else 0.6f),
                        textStyle = LiftTextStyle.No3,
                        color = textColor,
                        textAlign = TextAlign.Center
                    )
                }
                Spacer(modifier = modifier.height(LiftTheme.space.space10))
            }
        }
    }
}


