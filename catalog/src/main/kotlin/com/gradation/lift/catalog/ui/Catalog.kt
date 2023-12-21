package com.gradation.lift.catalog.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import com.gradation.lift.designsystem.component.button.LiftDefaultButton
import com.gradation.lift.designsystem.component.button.LiftErrorButton
import com.gradation.lift.designsystem.component.button.LiftGoogleLoginButton
import com.gradation.lift.designsystem.component.button.LiftKakaoLoginButton
import com.gradation.lift.designsystem.component.button.LiftNaverLoginButton
import com.gradation.lift.designsystem.component.button.LiftPrimaryButton
import com.gradation.lift.designsystem.component.button.LiftSmallButton
import com.gradation.lift.designsystem.component.button.LiftSolidButton
import com.gradation.lift.designsystem.component.container.LiftDefaultContainer
import com.gradation.lift.designsystem.component.container.LiftEmptyContainer
import com.gradation.lift.designsystem.component.container.LiftPrimaryContainer
import com.gradation.lift.designsystem.component.container.LiftSecondaryContainer
import com.gradation.lift.designsystem.component.filter.LiftLabelFilterContainer
import com.gradation.lift.designsystem.component.filter.LiftSortFilterContainer
import com.gradation.lift.designsystem.component.filter.LiftWeekdayFilterContainer
import com.gradation.lift.designsystem.component.label.RoutineLabel
import com.gradation.lift.designsystem.component.progress.LiftProgressCircleLabel
import com.gradation.lift.designsystem.component.progress.ProgressCircleState
import com.gradation.lift.designsystem.component.selector.LiftIconSelector
import com.gradation.lift.designsystem.component.selector.LiftPrimarySelector
import com.gradation.lift.designsystem.component.snackbar.SnackbarComponent
import com.gradation.lift.designsystem.component.text.LiftMultiStyleText
import com.gradation.lift.designsystem.component.text.LiftText
import com.gradation.lift.designsystem.component.text.LiftTextStyle
import com.gradation.lift.designsystem.component.text.TextWithStyle
import com.gradation.lift.designsystem.component.textField.LiftDefaultInputTextField
import com.gradation.lift.designsystem.component.textField.LiftPasswordInputTextField
import com.gradation.lift.designsystem.component.textField.LiftSearchInputTextField
import com.gradation.lift.designsystem.component.topBar.LiftTopBar
import com.gradation.lift.designsystem.theme.LiftTheme

@Composable
fun Catalog(
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .verticalScroll(rememberScrollState())
            .background(LiftTheme.colorScheme.no5)
            .fillMaxSize()
            .padding(LiftTheme.space.paddingSpace),
        verticalArrangement = Arrangement.spacedBy(LiftTheme.space.space24)
    ) {
        Column(
            modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(LiftTheme.space.space8)
        ) {
            LiftText(
                textStyle = LiftTextStyle.No1,
                text = "No1",
                color = LiftTheme.colorScheme.no9,
                textAlign = TextAlign.Left
            )
            LiftText(
                textStyle = LiftTextStyle.No2,
                text = "No2",
                color = LiftTheme.colorScheme.no9,
                textAlign = TextAlign.Left
            )
            LiftText(
                textStyle = LiftTextStyle.No3,
                text = "No3",
                color = LiftTheme.colorScheme.no9,
                textAlign = TextAlign.Left
            )
            LiftText(
                textStyle = LiftTextStyle.No4,
                text = "No4",
                color = LiftTheme.colorScheme.no9,
                textAlign = TextAlign.Left
            )
            LiftText(
                textStyle = LiftTextStyle.No5,
                text = "No5",
                color = LiftTheme.colorScheme.no9,
                textAlign = TextAlign.Left
            )
            LiftText(
                textStyle = LiftTextStyle.No6,
                text = "No6",
                color = LiftTheme.colorScheme.no9,
                textAlign = TextAlign.Left
            )
            LiftText(
                textStyle = LiftTextStyle.No7,
                text = "No7",
                color = LiftTheme.colorScheme.no9,
                textAlign = TextAlign.Left
            )
            LiftText(
                textStyle = LiftTextStyle.No8,
                text = "No8",
                color = LiftTheme.colorScheme.no9,
                textAlign = TextAlign.Left
            )
        }
        Column(
            modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(LiftTheme.space.space8)
        ) {
            LiftMultiStyleText(
                modifier,
                LiftTheme.colorScheme.no9,
                LiftTextStyle.No3,
                listOf(
                    TextWithStyle(text = "텍스트"),
                    TextWithStyle(
                        text = "스타일",
                        color = LiftTheme.colorScheme.no4,
                        style = LiftTextStyle.No2
                    )
                ),
                TextAlign.Start
            )
        }
        Column(
            modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(LiftTheme.space.space8)
        ) {
            LiftSolidButton(modifier, "버튼", true) {}
            LiftSolidButton(modifier, "버튼", false) {}
            Row(horizontalArrangement = Arrangement.spacedBy(LiftTheme.space.space12)) {
                LiftSolidButton(modifier.weight(1f), "버튼", true) {}
                LiftSolidButton(modifier.weight(1f), "버튼", false) {}
            }
        }
        Column(
            modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(LiftTheme.space.space8)
        ) {
            LiftDefaultButton(modifier, "버튼", true) {}
            LiftDefaultButton(modifier, "버튼", false) {}
            Row(horizontalArrangement = Arrangement.spacedBy(LiftTheme.space.space12)) {
                LiftDefaultButton(modifier.weight(1f), "버튼", true) {}
                LiftDefaultButton(modifier.weight(1f), "버튼", false) {}
            }
        }
        Column(
            modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(LiftTheme.space.space8)
        ) {
            LiftErrorButton(modifier, "버튼", true) {}
            LiftErrorButton(modifier, "버튼", false) {}
            Row(horizontalArrangement = Arrangement.spacedBy(LiftTheme.space.space12)) {
                LiftErrorButton(modifier.weight(1f), "버튼", true) {}
                LiftErrorButton(modifier.weight(1f), "버튼", false) {}
            }
        }
        Column(
            modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(LiftTheme.space.space8)
        ) {
            LiftPrimaryButton(modifier, "버튼", true) {}
            LiftPrimaryButton(modifier, "버튼", false) {}
            Row(horizontalArrangement = Arrangement.spacedBy(LiftTheme.space.space12)) {
                LiftPrimaryButton(modifier.weight(1f), "버튼", true) {}
                LiftPrimaryButton(modifier.weight(1f), "버튼", false) {}
            }
        }
        Column(
            modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(LiftTheme.space.space8)
        ) {
            LiftNaverLoginButton(modifier) {}
            LiftGoogleLoginButton(modifier) {}
            LiftKakaoLoginButton(modifier) {}
        }

        Column(
            modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(LiftTheme.space.space8)
        ) {
            LiftSmallButton(modifier, "버튼") {}
        }
        Column(
            modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(LiftTheme.space.space8)
        ) {
            Row(horizontalArrangement = Arrangement.spacedBy(LiftTheme.space.space12)) {
                LiftPrimarySelector(modifier.weight(1f), "선택", true) {}
                LiftPrimarySelector(modifier.weight(1f), "비선택", false) {}
            }

            Row(horizontalArrangement = Arrangement.spacedBy(LiftTheme.space.space12)) {
                LiftIconSelector(modifier.weight(1f), { RoutineLabel(modifier, 1) }, true) {}
                LiftIconSelector(modifier.weight(1f), { RoutineLabel(modifier, 2) }, false) {}
            }
            Row(horizontalArrangement = Arrangement.spacedBy(LiftTheme.space.space12)) {
                LiftIconSelector(modifier.weight(1f), { RoutineLabel(modifier, 3) }, false) {}
                LiftIconSelector(modifier.weight(1f), { RoutineLabel(modifier, 4) }, false) {}
            }
        }

        Column(
            modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(LiftTheme.space.space8)
        ) {
            Row(horizontalArrangement = Arrangement.spacedBy(LiftTheme.space.space8)) {
                LiftProgressCircleLabel(modifier, ProgressCircleState.Done, 1)
                LiftProgressCircleLabel(modifier, ProgressCircleState.Done, 2)
                LiftProgressCircleLabel(modifier, ProgressCircleState.Current, 3)
                LiftProgressCircleLabel(modifier, ProgressCircleState.None, 4)
                LiftProgressCircleLabel(modifier, ProgressCircleState.None, 5)
            }
        }
        Column(
            modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(LiftTheme.space.space8)
        ) {
            var value: String by remember { mutableStateOf("") }
            val onValueChange: (String) -> Unit = { value = it }
            LiftDefaultInputTextField(
                modifier = modifier,
                value = value,
                onValueChange = onValueChange,
                onValueClear = { value = "" },
                placeHolderValue = "Place Holder"
            )
            var errorValue: String by remember { mutableStateOf("") }
            val onErrorValueChange: (String) -> Unit = { errorValue = it }
            LiftDefaultInputTextField(
                modifier = modifier,
                value = errorValue,
                onValueClear = { errorValue = "" },
                onValueChange = onErrorValueChange,
                placeHolderValue = "Place Holder",
                isError = true,
            )
            var validValue: String by remember { mutableStateOf("") }
            val onValidValueChange: (String) -> Unit = { validValue = it }
            LiftDefaultInputTextField(
                modifier = modifier,
                value = validValue,
                onValueClear = { validValue = "" },
                onValueChange = onValidValueChange,
                placeHolderValue = "Place Holder",
                isError = false,
                isValid = true,
            )
            LiftDefaultInputTextField(
                modifier = modifier,
                value = "텍스트 필드",
                onValueChange = { },
                placeHolderValue = "",
                isError = false,
                isValid = false,
                enabled = false,
            )

            var passwordValue: String by remember { mutableStateOf("") }
            val onPasswordValueChange: (String) -> Unit = { passwordValue = it }
            LiftPasswordInputTextField(
                modifier = modifier,
                value = passwordValue,
                onValueChange = onPasswordValueChange,
                onValueClear = { passwordValue = "" },
                placeHolderValue = "Place Holder"
            )


            var searchValue: String by remember { mutableStateOf("") }
            val onSearchValueChange: (String) -> Unit = { searchValue = it }
            LiftSearchInputTextField(
                modifier = modifier,
                value = searchValue,
                onValueChange = onSearchValueChange,
                onValueClear = { searchValue = "" },
                placeHolderValue = "검색하실 단어를 입력해주세요"
            )
        }

        Column(
            modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(LiftTheme.space.space8)
        ) {
            SnackbarComponent(modifier, "인터넷이 불안정합니다.\n인터넷 연결상태를 확인해주세요") {}
        }
        Column(
            modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(LiftTheme.space.space8)
        ) {
            LiftSortFilterContainer(modifier = modifier, sortType = "전체")
            LiftWeekdayFilterContainer(modifier = modifier, weekdayType = "월,화,수,목,금,토")
            LiftLabelFilterContainer(modifier = modifier, labelType = setOf(1, 2, 3))
        }
        Column(
            modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(LiftTheme.space.space8)
        ) {
            LiftTopBar(
                modifier = modifier,
                title = "타이틀",
                onClick = {}
            )
        }
        Column(
            modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(LiftTheme.space.space8)
        ) {
            LiftDefaultContainer(
                modifier = modifier
                    .fillMaxWidth()
                    .height(LiftTheme.space.space120),
                content = {}
            )

            LiftPrimaryContainer(
                modifier = modifier
                    .fillMaxWidth()
                    .height(LiftTheme.space.space120),
                content = {}
            )

            LiftSecondaryContainer(
                modifier = modifier
                    .fillMaxWidth()
                    .height(LiftTheme.space.space120),
                content = {}
            )
            LiftEmptyContainer(
                modifier = modifier
                    .fillMaxWidth()
                    .height(LiftTheme.space.space120),
                content = {}
            )

        }

    }
}