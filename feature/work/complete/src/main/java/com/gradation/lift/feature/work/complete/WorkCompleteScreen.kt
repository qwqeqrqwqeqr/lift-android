package com.gradation.lift.feature.work.complete

import android.annotation.SuppressLint
import android.os.Build
import androidx.activity.compose.BackHandler
import androidx.annotation.RequiresApi
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.gradation.lift.designsystem.R
import com.gradation.lift.designsystem.component.LiftButton
import com.gradation.lift.designsystem.component.LiftTextField
import com.gradation.lift.designsystem.resource.LiftIcon
import com.gradation.lift.designsystem.theme.LiftMaterialTheme
import com.gradation.lift.designsystem.theme.LiftTheme
import com.gradation.lift.feature.work.work.data.model.WorkRestTime
import com.gradation.lift.feature.work.work.data.viewmodel.WorkSharedViewModel
import com.gradation.lift.model.history.CreateHistoryRoutine
import com.gradation.lift.model.history.History
import com.gradation.lift.model.routine.CreateRoutine
import com.gradation.lift.model.utils.ModelDataGenerator.History.createHistoryModel
import com.gradation.lift.model.utils.ModelDataGenerator.History.createHistoryRoutineModel
import com.gradation.lift.model.utils.ModelDataGenerator.History.historyRoutineModel1
import com.gradation.lift.model.utils.ModelDataGenerator.History.historyRoutineModel2
import com.gradation.lift.model.work.WorkSet
import com.gradation.lift.navigation.Router
import com.gradation.lift.navigation.navigation.navigateToLoginComplete
import com.gradation.lift.ui.utils.toText
import kotlinx.datetime.LocalTime

@RequiresApi(Build.VERSION_CODES.O)
@SuppressLint("UnrememberedGetBackStackEntry")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WorkCompleteRoute(
    navController: NavController,
    navigateWorkToMain: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: WorkCompleteViewModel = hiltViewModel(),
) {

    val workBackStackEntry =
        remember { navController.getBackStackEntry(Router.WORK_GRAPH_NAME) }
    val sharedViewModel: WorkSharedViewModel = hiltViewModel(workBackStackEntry)

    val workTime by sharedViewModel.workRestTime.collectAsStateWithLifecycle()
    val historyRoutineList by sharedViewModel.historyRoutine.collectAsStateWithLifecycle()
    val score by viewModel.score.collectAsStateWithLifecycle()
    val commentText by viewModel.comment.collectAsStateWithLifecycle()
    val navigateCondition by viewModel.navigateCondition.collectAsStateWithLifecycle()

    val updateScore = viewModel.updateScore()
    val updateCommentText = viewModel.updateComment()
    val createWorkHistory = viewModel.createWorkHistory(
        comment = commentText,
        score = score,
        workTime = workTime.workTime,
        restTime = workTime.restTime,
        totalTime = workTime.totalTime,
        historyRoutine = historyRoutineList
    )
    val scrollState: ScrollState = rememberScrollState()

    WorkCompleteScreen(
        modifier = modifier,
        onClickCompleteButton = createWorkHistory,

        workTime = workTime,
        score = score,
        commentText = commentText,
        historyRoutineList = historyRoutineList,

        onClickStar = updateScore,
        updateCommentText = updateCommentText,
        scrollState = scrollState
    )

    LaunchedEffect(true) {
        sharedViewModel.stopTime()
    }

    when (navigateCondition) {
        false -> {}
        true -> {
            LaunchedEffect(true) {
                navigateWorkToMain()
            }
        }
    }



    BackHandler(onBack = createWorkHistory)

}

@ExperimentalMaterial3Api
@Composable
fun WorkCompleteScreen(
    modifier: Modifier = Modifier,
    onClickCompleteButton: () -> Unit,
    workTime: WorkRestTime,
    score: Int,
    commentText: String,
    onClickStar: (Int) -> Unit,
    updateCommentText: (String) -> Unit,
    historyRoutineList: List<CreateHistoryRoutine>,
    scrollState: ScrollState
) {

    Surface(
        color = LiftTheme.colorScheme.no5,
        modifier = modifier.fillMaxSize()
    ) {
        Column(
            modifier = modifier.verticalScroll(scrollState)
        ) {
            Box(
                modifier = modifier.fillMaxWidth()
            ) {
                Image(
                    painter = painterResource(R.drawable.halo),
                    contentDescription = "",
                    modifier = modifier.fillMaxWidth()
                )
                Image(
                    painter = painterResource(R.drawable.trophy),
                    contentDescription = "",
                    modifier = modifier
                        .size(160.dp)
                        .align(Alignment.BottomCenter)
                )
            }
            Column(
                modifier = modifier
                    .padding(16.dp)
                    .fillMaxSize(),
            ) {
                Text(
                    text = buildAnnotatedString {
                        append("운동을 ")
                        withStyle(
                            style = SpanStyle(
                                color = LiftTheme.colorScheme.no4,
                                fontWeight = FontWeight.Bold
                            ),
                        ) {
                            append("완료")
                        }
                        append("하였습니다!")
                    },
                    style = LiftTheme.typography.no1.copy(
                        fontWeight = FontWeight(400)
                    ),
                    color = LiftTheme.colorScheme.no9,
                    modifier = modifier.align(Alignment.CenterHorizontally)
                )
                Spacer(modifier = modifier.padding(8.dp))
                Row(
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Column(
                        modifier = modifier
                            .background(LiftTheme.colorScheme.no5)
                            .border(
                                width = 2.dp,
                                color = LiftTheme.colorScheme.no8,
                                shape = RoundedCornerShape(16.dp)
                            )
                            .weight(1f)
                            .fillMaxWidth()
                            .padding(vertical = 12.dp, horizontal = 16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically

                        ) {
                            Icon(
                                painter = painterResource(LiftIcon.Muscle),
                                contentDescription = "",
                                tint = LiftTheme.colorScheme.no20
                            )
                            Spacer(modifier = modifier.padding(1.dp))
                            Text(
                                text = "총 소요시간",
                                style = LiftTheme.typography.no6,
                                color = LiftTheme.colorScheme.no11
                            )
                        }
                        Text(
                            text = workTime.totalTime.toText(),
                            style = LiftTheme.typography.no3,
                            color = LiftTheme.colorScheme.no9
                        )
                    }
                    Column(
                        modifier = modifier
                            .background(LiftTheme.colorScheme.no5)
                            .border(
                                width = 2.dp,
                                color = LiftTheme.colorScheme.no8,
                                shape = RoundedCornerShape(16.dp)
                            )
                            .weight(1f)
                            .fillMaxWidth()
                            .padding(vertical = 12.dp, horizontal = 16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically

                        ) {
                            Icon(
                                painter = painterResource(LiftIcon.Timer),
                                contentDescription = "",
                                tint = LiftTheme.colorScheme.no4
                            )
                            Spacer(modifier = modifier.padding(1.dp))
                            Text(
                                text = "총 휴식시간",
                                style = LiftTheme.typography.no6,
                                color = LiftTheme.colorScheme.no11
                            )
                        }
                        Text(
                            text = workTime.restTime.toText(),
                            style = LiftTheme.typography.no3,
                            color = LiftTheme.colorScheme.no9
                        )
                    }
                }
                Spacer(modifier = modifier.padding(16.dp))
                Text(
                    text = "평가하기",
                    style = LiftTheme.typography.no3,
                    color = LiftTheme.colorScheme.no9,
                    modifier = modifier.align(Alignment.Start)
                )
                Spacer(modifier = modifier.padding(8.dp))
                Row(
                    horizontalArrangement = Arrangement.spacedBy(
                        8.dp,
                        Alignment.CenterHorizontally
                    ),
                    modifier = modifier.fillMaxWidth(),
                ) {
                    repeat(5) {
                        Image(
                            painter = if (it < score) painterResource(R.drawable.star_on) else painterResource(
                                R.drawable.star_off
                            ),
                            contentDescription = "",
                            modifier = modifier
                                .size(36.dp)
                                .clickable(
                                    onClick = { onClickStar(it + 1) }
                                )
                        )
                    }
                }

                Spacer(modifier = modifier.padding(16.dp))
                Text(
                    text = "한 줄 메모",
                    style = LiftTheme.typography.no3,
                    color = LiftTheme.colorScheme.no9,
                    modifier = modifier.align(Alignment.Start)
                )

                Spacer(modifier = modifier.padding(4.dp))
                LiftTextField(
                    value = commentText,
                    onValueChange = updateCommentText,
                    modifier = modifier.fillMaxWidth(),
                    placeholder = {
                        Text(
                            text = "간단한 메모를 남겨주세요.",
                            style = LiftTheme.typography.no6,
                        )
                    },
                    singleLine = true,
                )

                Spacer(modifier = modifier.padding(16.dp))
                Text(
                    text = "운동 기록",
                    style = LiftTheme.typography.no3,
                    color = LiftTheme.colorScheme.no9,
                    modifier = modifier.align(Alignment.Start)
                )
                Spacer(modifier = modifier.padding(4.dp))

                historyRoutineList.forEach { historyRoutine ->
                    Column(
                        modifier = modifier
                            .background(LiftTheme.colorScheme.no5)
                            .border(
                                width = 1.dp,
                                color = LiftTheme.colorScheme.no8,
                                shape = RoundedCornerShape(16.dp)
                            )
                            .fillMaxWidth()
                            .padding(vertical = 12.dp, horizontal = 16.dp),
                    ) {
                        Text(
                            text = historyRoutine.workCategory,
                            color = LiftTheme.colorScheme.no9,
                            style = LiftTheme.typography.no3
                        )
                        with(historyRoutine.workSetList) {
                            Row(
                                horizontalArrangement = Arrangement.SpaceBetween,
                                modifier = modifier.fillMaxWidth()
                            ) {
                                Text(
                                    text = "세트/평균횟수",
                                    style = LiftTheme.typography.no6,
                                    color = LiftTheme.colorScheme.no11
                                )
                                Text(
                                    text = buildAnnotatedString {
                                        withStyle(
                                            style = SpanStyle(fontWeight = FontWeight.Bold),
                                        ) {
                                            append("$size")
                                        }
                                        append(" Set  ")
                                        withStyle(
                                            style = SpanStyle(fontWeight = FontWeight.Bold),
                                        ) {
                                            append("${(sumOf { it.repetition } / size)}")
                                        }
                                        append(" Reps")

                                    },
                                    style = LiftTheme.typography.no6,
                                    color = LiftTheme.colorScheme.no11
                                )

                            }
                            Row(
                                horizontalArrangement = Arrangement.SpaceBetween,
                                modifier = modifier.fillMaxWidth()
                            ) {
                                Text(
                                    text = "최대 무게",
                                    style = LiftTheme.typography.no6,
                                    color = LiftTheme.colorScheme.no11
                                )
                                Text(
                                    text = buildAnnotatedString {
                                        withStyle(
                                            style = SpanStyle(fontWeight = FontWeight.Bold),
                                        ) {
                                            append((maxBy { it.weight }.weight).toText())
                                        }
                                        append("kg")
                                    },
                                    style = LiftTheme.typography.no6,
                                    color = LiftTheme.colorScheme.no11
                                )

                            }
                        }
                    }
                    Spacer(modifier = modifier.padding(4.dp))
                }
                Spacer(modifier = modifier.padding(16.dp))
                LiftButton(
                    modifier = modifier.fillMaxWidth(),
                    onClick = onClickCompleteButton,
                ) {
                    Text(
                        text = "완료",
                        style = LiftTheme.typography.no3,
                        color = LiftTheme.colorScheme.no5,
                    )
                }
            }
        }
    }
}

@SuppressLint("UnrememberedMutableState")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview
fun WorkCompleteScreenPreview() {
    LiftMaterialTheme {
        WorkCompleteScreen(
            modifier = Modifier,
            onClickCompleteButton = {},
            workTime = WorkRestTime().copy(
                totalTime = LocalTime(2, 30),
                restTime = LocalTime(0, 10, 30),
            ),
            score = 4,
            commentText = "열심히 운동했어",
            updateCommentText = {},
            onClickStar = {},
            historyRoutineList = listOf(
                createHistoryRoutineModel,
                createHistoryRoutineModel,
                createHistoryRoutineModel
            ),
            scrollState = rememberScrollState()
        )
    }
}