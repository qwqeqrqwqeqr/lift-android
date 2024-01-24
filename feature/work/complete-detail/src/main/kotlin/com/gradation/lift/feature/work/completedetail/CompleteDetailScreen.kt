package com.gradation.lift.feature.work.completedetail

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.gradation.lift.common.utils.Validator
import com.gradation.lift.designsystem.component.LiftTextField
import com.gradation.lift.designsystem.component.button.LiftSolidButton
import com.gradation.lift.designsystem.component.container.LiftDefaultContainer
import com.gradation.lift.designsystem.component.snackbar.LiftSnackBar
import com.gradation.lift.designsystem.component.text.LiftMultiStyleText
import com.gradation.lift.designsystem.component.text.LiftText
import com.gradation.lift.designsystem.component.text.LiftTextStyle
import com.gradation.lift.designsystem.component.text.TextWithStyle
import com.gradation.lift.designsystem.component.textField.LiftDefaultInputTextField
import com.gradation.lift.designsystem.resource.LiftIcon
import com.gradation.lift.designsystem.theme.LiftMaterialTheme
import com.gradation.lift.designsystem.theme.LiftTheme
import com.gradation.lift.feature.work.common.data.model.WorkRestTime
import com.gradation.lift.feature.work.completedetail.data.state.CompleteDetailScreenState
import com.gradation.lift.feature.work.completedetail.data.state.HistoryInfoState
import com.gradation.lift.feature.work.completedetail.data.state.rememberCompleteDetailScreenState
import com.gradation.lift.model.model.history.CreateHistoryRoutine
import com.gradation.lift.ui.mapper.toDayMonthYearText
import com.gradation.lift.ui.mapper.toText
import com.gradation.lift.ui.modifier.noRippleClickable
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.toJavaLocalDateTime
import kotlinx.datetime.toKotlinLocalDateTime
import java.time.format.DateTimeFormatter

@Composable
internal fun CompleteDetailScreen(
    modifier: Modifier = Modifier,
    score: Int,
    comment: String,
    commentValidator: Validator,
    currentTime: LocalDateTime,
    createHistory: () -> Unit,
    historyInfoState: HistoryInfoState,
    completeDetailScreenState: CompleteDetailScreenState,
) {
    Scaffold(
        modifier = modifier,
        snackbarHost = {
            LiftSnackBar(snackbarHostState = completeDetailScreenState.snackbarHostState)
        }
    ) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .background(LiftTheme.colorScheme.no5)
                .padding(it)
                .padding(
                    top = LiftTheme.space.space64,
                    start = LiftTheme.space.space20,
                    end = LiftTheme.space.space20
                ),
            verticalArrangement = Arrangement.spacedBy(LiftTheme.space.space32),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column(
                modifier = modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(LiftTheme.space.space32),
            ) {
                Column(
                    modifier = modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(LiftTheme.space.space4),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    LiftText(
                        textStyle = LiftTextStyle.No5,
                        text = currentTime.toDayMonthYearText(),
                        color = LiftTheme.colorScheme.no13,
                        textAlign = TextAlign.Left
                    )
                    LiftMultiStyleText(
                        defaultColor = LiftTheme.colorScheme.no9,
                        defaultTextStyle = LiftTextStyle.No1,
                        textAlign = TextAlign.Center,
                        textWithStyleList = listOf(
                            TextWithStyle(
                                text = "오늘의 운동은 어떠셨나요?\n",
                            ),
                            TextWithStyle(
                                text = "기록",
                                color = LiftTheme.colorScheme.no4
                            ),
                            TextWithStyle(text = "으로 남겨보세요.")
                        )
                    )
                }
                Row(
                    modifier = modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(
                        LiftTheme.space.space4,
                        Alignment.CenterHorizontally
                    ),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    repeat(5) { targetScore ->
                        AnimatedContent(
                            targetState = targetScore + 1 <= score,
                            label = "scoreAnimation",
                        ) { it ->
                            Icon(
                                modifier = modifier
                                    .size(LiftTheme.space.space36)
                                    .noRippleClickable { historyInfoState.updateScore(targetScore + 1) },
                                painter = painterResource(id = LiftIcon.StarFilled),
                                contentDescription = "star",
                                tint = if (it) LiftTheme.colorScheme.no26 else LiftTheme.colorScheme.no1
                            )
                        }

                    }
                    Row(
                        modifier = modifier,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        AnimatedContent(
                            targetState = score,
                            label = "scoreTextAnimation",
                            transitionSpec = {
                                slideIntoContainer(
                                    towards = AnimatedContentTransitionScope.SlideDirection.Up,
                                    animationSpec = tween(durationMillis = 300)
                                ) togetherWith ExitTransition.None
                            },
                        ) { score ->
                            LiftText(
                                textStyle = LiftTextStyle.No3,
                                text = "$score",
                                color = LiftTheme.colorScheme.no9,
                                textAlign = TextAlign.Left
                            )
                        }
                        LiftText(
                            textStyle = LiftTextStyle.No3,
                            text = "/5",
                            color = LiftTheme.colorScheme.no9,
                            textAlign = TextAlign.Left
                        )

                    }
                }
            }
            Column(
                modifier = modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(LiftTheme.space.space8),
            ) {
                LiftText(
                    textStyle = LiftTextStyle.No3,
                    text = "메모",
                    color = LiftTheme.colorScheme.no3,
                    textAlign = TextAlign.Left
                )
                Column(verticalArrangement = Arrangement.spacedBy(LiftTheme.space.space4)) {
                    LiftDefaultInputTextField(
                        modifier = modifier.height(LiftTheme.space.space92),
                        value = comment,
                        singleLine = false,
                        isError = !commentValidator.status,
                        onValueChange = historyInfoState.updateComment,
                        placeHolderValue = "오늘의 경험을 남겨주세요. (20자 내)",
                        keyboardOptions = KeyboardOptions(
                            imeAction = ImeAction.Done,
                            keyboardType = KeyboardType.Text
                        ),
                        keyboardActions = KeyboardActions(
                            onDone = {
                                completeDetailScreenState.focusManager.clearFocus()
                            }
                        ),
                    )
                    AnimatedVisibility(visible = !commentValidator.status) {
                        LiftText(
                            modifier = modifier.fillMaxWidth(),
                            textStyle = LiftTextStyle.No7,
                            text = commentValidator.message,
                            color = LiftTheme.colorScheme.no12,
                            textAlign = TextAlign.Start
                        )
                    }

                }
            }
            LiftSolidButton(
                text = "완료",
                onClick = createHistory
            )
        }
    }
}

@Composable
@Preview
fun CompleteScreenDetailPreview(modifier: Modifier = Modifier) {
    LiftMaterialTheme {
        CompleteDetailScreen(
            modifier = modifier,
            score = 3,
            comment = "",
            commentValidator = Validator(true, ""),
            currentTime = java.time.LocalDateTime.now().toKotlinLocalDateTime(),
            createHistory = {},
            historyInfoState = HistoryInfoState(rememberCoroutineScope()),
            completeDetailScreenState = rememberCompleteDetailScreenState()
        )
    }
}
